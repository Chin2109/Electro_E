package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class BrandsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long brandId;

    private String name;

    private String urlImage;

    private String description;

}
