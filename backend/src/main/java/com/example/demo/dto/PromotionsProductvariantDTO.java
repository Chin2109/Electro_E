package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class PromotionsProductvariantDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long promoId;

    private Long variantId;

}
