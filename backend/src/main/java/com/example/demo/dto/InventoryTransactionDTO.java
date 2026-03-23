package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class InventoryTransactionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long transactionId;

    private Long variantId;

    private Long quantity;

    private Long refId;

    private String refType;

    private Date createdAt;

}
