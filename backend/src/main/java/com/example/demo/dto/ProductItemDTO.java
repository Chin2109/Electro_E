package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProductItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Long provarId;

    private Long warehouseId;

    private Long orderId;

    private Long status;

    private String serialNumber;

}
