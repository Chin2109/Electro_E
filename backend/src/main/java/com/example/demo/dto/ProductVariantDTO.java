package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductVariantDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long variantId;

    private Long productId;

    private Long status;

    private BigDecimal buyingPrice;

    private BigDecimal sellingPrice;

    private Long stockQuantity;

    private Long soldQuantity;

    private String sku;

}
