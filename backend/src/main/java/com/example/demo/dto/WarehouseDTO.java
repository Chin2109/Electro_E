package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class WarehouseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long warehouseId;

    private String name;

    private String type;

    private String phone;

    private String address;

    private Long status;

}
