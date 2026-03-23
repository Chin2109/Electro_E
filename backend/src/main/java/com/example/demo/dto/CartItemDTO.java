package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class CartItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long cartItemId;

    private Long cartId;

    private Long variantId;

    private Long quantity;

}
