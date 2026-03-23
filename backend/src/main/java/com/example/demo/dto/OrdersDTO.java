package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrdersDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long orderId;

    private String code;

    private Long userId;

    private Long stockoutId;

    private Long warehouseId;

    private Long shipperId;

    private Date createdAtDatetime;

    private Date completedAt;

    private Long statusOrder;

    private Long deliveryMethod;

    private String toPhone;

    private String toName;

    private String toAddress;

    private String toWard;

    private String toDistrict;

    private String toProvince;

    private BigDecimal totalAmount;

    private BigDecimal shippingCost;

    private BigDecimal totalPay;

    private Long paymentMethod;

    private Long paymentStatus;

    private String note;

}
