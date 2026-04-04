package com.example.demo.service;

import com.example.demo.dto.request.auth.AuthenticationRequest;
import com.example.demo.dto.request.auth.IntrospectRequest;
import com.example.demo.dto.request.auth.RegisterRequest;
import com.example.demo.dto.request.auth.RoleRequestDTO;
import com.example.demo.dto.response.auth.AuthenticationResponse;
import com.example.demo.dto.response.auth.IntrospectResponse;
import com.example.demo.dto.response.auth.RoleResponseDTO;
import com.example.demo.entity.*;
import com.example.demo.enums.ErrorCode;
import com.example.demo.exception.AppException;
import com.example.demo.repository.*;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.transaction.Transactional;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {
    private final RolesRepository rolesRepository;
    private final PermissionsRepository permissionsRepository;
    private final RolePermissionsRepository rolePermissionsRepository;
    private final UsersRepository usersRepository;
    private final UserRolesRepository userRolesRepository;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public AuthenticationService(RolesRepository rolesRepository,
                                 PermissionsRepository permissionsRepository,
                                 RolePermissionsRepository rolePermissionsRepository,
                                 UsersRepository usersRepository,
                                 UserRolesRepository userRolesRepository) {
        this.permissionsRepository = permissionsRepository;
        this.rolesRepository = rolesRepository;
        this.rolePermissionsRepository = rolePermissionsRepository;
        this.usersRepository = usersRepository;
        this.userRolesRepository = userRolesRepository;
    }


    public List<Permissions> getAllPermission() {
        return permissionsRepository.findAll();
    }

    public RoleResponseDTO getRoleDetail(Long roleId) {

        Roles role = rolesRepository.getReferenceById(roleId);

        // Lấy danh sách Permission chi tiết liên kết với Role này
        List<Permissions> permissionsList = permissionsRepository.findAllByRoleId(roleId);

        // Map sang DTO
        List<RoleResponseDTO.PermissionResponseDTO> pDTOs = permissionsList.stream()
                .map(p -> RoleResponseDTO.PermissionResponseDTO.builder()
                        .permissionId(p.getPermissionId())
                        .name(p.getName())
                        .resource(p.getResource())
                        .description(p.getDescription())
                        .build())
                .collect(Collectors.toList());

        return RoleResponseDTO.builder()
                .roleId(role.getRoleId())
                .name(role.getName())
                .description(role.getDescription())
                .permissions(pDTOs)
                .build();
    }

    @Transactional
    public RoleResponseDTO createRole(RoleRequestDTO dto) {
        // 1. Lưu Role mới
        Roles role = Roles.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
        Roles savedRole = rolesRepository.save(role);

        // 2. Gán danh sách quyền (Batch Insert)
        if (dto.getPermissionIds() != null && !dto.getPermissionIds().isEmpty()) {
            List<RolePermissions> rpList = dto.getPermissionIds().stream()
                    .map(pId -> new RolePermissions().setRolePermissionsId(
                            new RolePermissionsId(savedRole.getRoleId(), pId)))
                    .collect(Collectors.toList());
            rolePermissionsRepository.saveAll(rpList);
        }

        // 3. Lấy lại dữ liệu đầy đủ để trả về Response
        return getRoleDetail(savedRole.getRoleId());
    }

    public List<RoleResponseDTO> getAllRole() {
        List<Roles> roles = rolesRepository.findAll();
        List<RoleResponseDTO> returnList = new ArrayList<>();

        for(Roles role : roles) {
            returnList.add(getRoleDetail(role.getRoleId()));
        }

        return returnList;
    }

    // --- LOGIC ĐĂNG NHẬP ---
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = usersRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // Kiểm tra status (Ví dụ: 0 là LOCK, 1 là ACTIVE)
        if (user.getStatus() == 0L) {
            throw new AppException(ErrorCode.USER_LOCKED);
        }

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    // --- LOGIC TẠO TOKEN ---
    private String generateToken(Users user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(UUID.randomUUID().toString())
                .subject(user.getUsername())
                .issuer("electro.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(10, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("userId", user.getUserId())
                .claim("scope", buildScope(user)) // Gộp Role & Permission
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    // --- LOGIC XÁC THỰC TOKEN (VERIFY) ---
    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);

        // 1. Kiểm tra chữ ký và hết hạn
        if (!(verified && expiryTime.after(new Date())))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        // 2. Kiểm tra Redis xem token đã logout chưa
        String jti = signedJWT.getJWTClaimsSet().getJWTID();
        if (Boolean.TRUE.equals(redisTemplate.hasKey(jti))) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;
    }

    // --- LOGIC LOGOUT (SỬ DỤNG REDIS) ---
    public void logout(String token) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(token);
            String jti = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            long ttl = expiryTime.getTime() - System.currentTimeMillis();

            if (ttl > 0) {
                // Lưu JTI vào Redis với thời gian sống còn lại của Token
                redisTemplate.opsForValue().set(jti, "revoked", ttl, TimeUnit.MILLISECONDS);
            }
        } catch (AppException e) {

        }
    }

    public IntrospectResponse introspect(IntrospectRequest request) {
        boolean valid = true;
        try {
            verifyToken(request.getToken());
        } catch (Exception e) {
            valid = false;
        }
        return IntrospectResponse.builder().valid(valid).build();
    }

    // --- LOGIC BUILD SCOPE (ROLE + PERMISSION) ---
    private String buildScope(Users user) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        // 1. Lấy danh sách Role từ bảng trung gian UserRoles
        List<UserRoles> userRoles = userRolesRepository.findAllByUser_UserId(user.getUserId());

        if (!userRoles.isEmpty()) {
            userRoles.forEach(ur -> {
                Roles role = ur.getRole();
                stringJoiner.add("ROLE_" + role.getName()); // Thêm Role

                // 2. Lấy Permission chi tiết của từng Role (Dùng method đã viết)
                List<Permissions> permissions = permissionsRepository.findAllByRoleId(role.getRoleId());
                if (!permissions.isEmpty()) {
                    permissions.forEach(p -> stringJoiner.add(p.getName())); // Thêm Permission
                }
            });
        }
        return stringJoiner.toString();
    }

    @Transactional
    public Users register(RegisterRequest request) {
        // 1. Kiểm tra username đã tồn tại chưa
        if (usersRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        // 2. Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 3. Tạo Entity Users từ Request
        Users newUser = Users.builder()
                .username(request.getUsername())
                .password(encodedPassword)
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .gender(request.getGender())
                .status(1L) // Mặc định là Active khi đăng ký xong
                .build();

        Users savedUser = usersRepository.save(newUser);

        // 4. Gán Role CUSTOMER cho User mới
        // Tìm Role có tên là 'CUSTOMER' trong bảng Roles
        Roles customerRole = rolesRepository.findByName("CUSTOMER")
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_ERROR));

        UserRoles userRole = UserRoles.builder()
                .id(new UserRolesId(savedUser.getUserId(), customerRole.getRoleId()))
                .user(savedUser)
                .role(customerRole)
                .build();

        userRolesRepository.save(userRole);

        return savedUser;
    }

}
