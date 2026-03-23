package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class SupplierDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long supplierId;

    private String name;

    private String companyName;

    private String taxCode;

    private String fax;

    private String contactEmail;

    private String contactPhone;

    private Long status;

    private String description;

}
