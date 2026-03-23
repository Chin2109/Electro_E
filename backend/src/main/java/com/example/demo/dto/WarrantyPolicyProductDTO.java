package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class WarrantyPolicyProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long warrantyId;

    private Long productId;

    private BigDecimal price;

}
