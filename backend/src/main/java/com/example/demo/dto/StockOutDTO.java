package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StockOutDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long stockoutId;

    private Long warehouseId;

    private Long orderId;

    private Long staffId;

    private Date exportDate;

    private Long typeOfExport;

    private Long totalQuantity;

    private String note;

    private Long status;

}
