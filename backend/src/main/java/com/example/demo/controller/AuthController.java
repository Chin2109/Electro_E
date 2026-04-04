package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.request.auth.AuthenticationRequest;
import com.example.demo.dto.request.auth.RegisterRequest;
import com.example.demo.dto.request.auth.RoleRequestDTO;
import com.example.demo.dto.response.auth.AuthenticationResponse;
import com.example.demo.dto.response.auth.RoleResponseDTO;
import com.example.demo.entity.Permissions;
import com.example.demo.entity.Users;
import com.example.demo.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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

    //auth/login
    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .message("New user created")
                .data(authenticationService.authenticate(request))
                .build();
    }

    //auth/logout
    @PostMapping("/logout")
    ApiResponse<String> logout(@RequestBody String token) throws ParseException, JOSEException {
        authenticationService.logout(token);

        return ApiResponse.<String>builder()
                .message("Log ")
                .data("Logout")
                .build();
    }

    //auth/register
    @PostMapping("/register")
    ApiResponse<Users> register(@RequestBody RegisterRequest request) {
        return ApiResponse.<Users>builder()
                .message("New user created")
                .data(authenticationService.register(request))
                .build();
    }

    //

}
