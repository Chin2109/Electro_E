package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class RolesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long roleId;

    private String name;

    private String description;

}
