package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WarrantyTicketDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long ticketId;

    private Long productitemId;

    private Long customerId;

    private Long warehouseId;

    private String issueDescription;

    private Long status;

    private Date receivedDate;

    private Date expectedReturn;

    private Date returnDate;

    private String technicianNote;

}
