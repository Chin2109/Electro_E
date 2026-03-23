package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PromotionsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long promoId;

    private String name;

    private Long type;

    private BigDecimal discountValue;

    private Boolean percent;

    private Date startDate;

    private Date endDate;

    private Boolean active;

    private BigDecimal applyCondition;

    private String note;

    private Long maxUsage;

    private Long currentUsage;

}
