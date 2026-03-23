package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PreorderDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long preorderId;

    private Long variantId;

    private Long userId;

    private String fullname;

    private String phoneNumber;

    private String email;

    private Long status;

    private Date createdAt;

    private String note;

}
