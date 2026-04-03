package com.example.demo.service;

import com.example.demo.dto.request.auth.RoleRequestDTO;
import com.example.demo.dto.response.auth.RoleResponseDTO;
import com.example.demo.entity.Permissions;
import com.example.demo.entity.RolePermissions;
import com.example.demo.entity.RolePermissionsId;
import com.example.demo.entity.Roles;
import com.example.demo.repository.PermissionsRepository;
import com.example.demo.repository.RolePermissionsRepository;
import com.example.demo.repository.RolesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {
    private final RolesRepository rolesRepository;
    private final PermissionsRepository permissionsRepository;
    private final RolePermissionsRepository rolePermissionsRepository;

    public AuthenticationService(RolesRepository rolesRepository,
                                 PermissionsRepository permissionsRepository,
                                 RolePermissionsRepository rolePermissionsRepository) {
        this.permissionsRepository = permissionsRepository;
        this.rolesRepository = rolesRepository;
        this.rolePermissionsRepository = rolePermissionsRepository;
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




}
