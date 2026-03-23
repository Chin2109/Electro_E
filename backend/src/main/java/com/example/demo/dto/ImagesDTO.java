package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class ImagesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long imageId;

    private Long variantId;

    private String path;

    private Boolean thumbnail;

}
