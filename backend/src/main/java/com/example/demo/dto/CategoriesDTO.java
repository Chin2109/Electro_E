package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class CategoriesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long categoryId;

    private String name;

    private Long status;

}
