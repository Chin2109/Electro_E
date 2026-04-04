package com.example.demo.dto.request.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    String username;
    String password;
    String fullName;
    String email;
    String phone;
    Long gender; // 1: Nam, 2: Nữ, 0: Khác
}
