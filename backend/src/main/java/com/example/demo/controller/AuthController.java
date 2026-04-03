package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.request.auth.RoleRequestDTO;
import com.example.demo.dto.response.auth.RoleResponseDTO;
import com.example.demo.entity.Permissions;
import com.example.demo.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/permission")
    public ApiResponse<List<Permissions>> getAllPermission() {
        return ApiResponse.<List<Permissions>>builder()
                .message("Permission list")
                .data(authenticationService.getAllPermission())
                .build();
    }

    @PostMapping("/role/create")
    public ApiResponse<RoleResponseDTO> createRole(@RequestBody RoleRequestDTO request) {
        return ApiResponse.<RoleResponseDTO>builder()
                .message("New Role")
                .data(authenticationService.createRole(request))
                .build();
    }

    @GetMapping("/role")
    public ApiResponse<List<RoleResponseDTO>> getAllRole() {
        return ApiResponse.<List<RoleResponseDTO>>builder()
                .message("Role list")
                .data(authenticationService.getAllRole())
                .build();
    }



}
