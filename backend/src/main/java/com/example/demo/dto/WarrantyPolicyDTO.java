package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class WarrantyPolicyDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long warrantId;

    private String name;

    private Long durationMonths;

    private String description;

}
