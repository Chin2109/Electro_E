package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CartDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long cartId;

    private Long userId;

    private Date createdAt;

}
