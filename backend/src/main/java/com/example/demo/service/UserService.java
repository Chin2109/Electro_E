package com.example.demo.service;

import com.example.demo.dto.request.user.UserCreationRequest;
import com.example.demo.dto.response.user.UserResponse;
import com.example.demo.entity.Roles;
import com.example.demo.entity.UserRoles;
import com.example.demo.entity.UserRolesId;
import com.example.demo.entity.Users;
import com.example.demo.enums.ErrorCode;
import com.example.demo.exception.AppException;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UserRolesRepository;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final UserRolesRepository userRolesRepository;

    public UserService(UsersRepository usersRepository,
                       RolesRepository rolesRepository,
                       UserRolesRepository userRolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.userRolesRepository = userRolesRepository;
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    public void setRole(Users user, List<Roles> roles) {
        List<UserRoles> userRoleList = new ArrayList<>();

        for(Roles role : roles) {
            UserRoles ur = new UserRoles();
            ur.setUser(user);
            ur.setRole(role);
            ur.setId(new UserRolesId(user.getUserId(), role.getRoleId()));

            userRoleList.add(ur);
        }

        userRolesRepository.saveAll(userRoleList);
    }

    public List<Roles> getRole(Users user) {
        var id = user.getUserId();

        List<UserRoles> userRoles = userRolesRepository.findByUser(user);

        return userRoles.stream()
                .map(UserRoles::getRole)
                .collect(Collectors.toList());
    }

    //create new user
    public UserResponse createRequest(UserCreationRequest request) {
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setGender(request.getGender());
        user.setFullName(request.getFullName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (usersRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        user = usersRepository.save(user);

        var roles = rolesRepository.findAllById(request.getUserRole());
        setRole(user,roles);

        return UserResponse.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .gender(user.getGender())
                .build();
    }
}
