package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class StockInItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long stockinItemId;

    private Long stockinId;

    private Long itemId;

    private BigDecimal importPrice;

}
