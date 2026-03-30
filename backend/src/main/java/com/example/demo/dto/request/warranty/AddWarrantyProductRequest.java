package com.example.demo.dto.request.warranty;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddWarrantyProductRequest {
    Long warrantyId;

    Long productId;

    BigDecimal price;
}
