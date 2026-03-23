package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProductCategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long productId;

    private Long categoryId;

}
