package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class StockOutItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long stockoutItemId;

    private Long stockoutId;

    private Long itemId;

}
