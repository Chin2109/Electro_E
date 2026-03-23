package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class StockInDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long stockinId;

    private Long supplierId;

    private Long warehouseId;

    private Long staffId;

    private BigDecimal totalAmount;

    private Long status;

    private Date importDate;

    private String note;

}
