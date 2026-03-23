package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserRolesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;

    private Long roleId;

}
