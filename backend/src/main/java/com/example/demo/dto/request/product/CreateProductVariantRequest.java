package com.example.demo.dto.request.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateProductVariantRequest {
    //để biết thêm cho product nào
    Long productId;

    BigDecimal buyingPrice;
    BigDecimal sellingPrice;
    String sku;
    List<Option> options;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Option {
        Long variantOptionId;   //thuộc tính nào (màu sắc, dung lượng)
        String value;           //giá trị
    }
}
