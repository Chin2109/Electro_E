package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProductVariantOptionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long variantId;

    private Long optionId;

}
