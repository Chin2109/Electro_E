package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProductSpecAttributeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long productId;

    private Long attributeId;

    private String value;

}
