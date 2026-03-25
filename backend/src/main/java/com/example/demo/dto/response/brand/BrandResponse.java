package com.example.demo.dto.response.brand;

import com.example.demo.entity.Products;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandResponse {
    Long brandId;
    String name;
    List<Products> products;
}
