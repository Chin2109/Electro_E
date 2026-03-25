package com.example.demo.dto.request.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateProductRequest {
    Long brandId;

    String slug;

    String name;

    String shortDescription;

    String description; //html technical description

    String article;     //html article include video youtube
}
