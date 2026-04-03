package com.example.demo.dto.response.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class RoleResponseDTO {
    private Long roleId;
    private String name;
    private String description;
    private List<PermissionResponseDTO> permissions;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PermissionResponseDTO {
        private Long permissionId;
        private String name;
        private String resource;
        private String description;
    }
}
