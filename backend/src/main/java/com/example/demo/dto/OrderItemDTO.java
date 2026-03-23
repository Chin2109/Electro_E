package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long orderItemId;

    private Long orderId;

    private Long itemId;

    private Long promoId;

    private BigDecimal unitPrice;

    private BigDecimal costPrice;

    private BigDecimal discountAmount;

    private BigDecimal total;

}
