package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UsersDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;

    private String username;

    private String password;

    private String fullName;

    private String email;

    private String phone;

    private Long gender;

    private Long status;

}
