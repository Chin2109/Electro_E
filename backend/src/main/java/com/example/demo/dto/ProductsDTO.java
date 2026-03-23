package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProductsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long productId;

    private Long brandId;

    private String name;

    private String shortDescription;

    private String description;

    private String article;

    private Long status;

}
