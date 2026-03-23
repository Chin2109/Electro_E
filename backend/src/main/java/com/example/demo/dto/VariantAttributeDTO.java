package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class VariantAttributeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long attributeId;

    private Long productId;

    private String code;

    private String name;

}
