package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class VariantOptionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long optionId;

    private Long attributeId;

    private String value;

}
