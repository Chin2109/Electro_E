package com.example.demo.dto.response.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductDetailResponse {
    Long productId;
    String productName;
    Long brandId;
    String brandName;
    String mainImg;
    String imgs;
    String technicalInfo;
    String article;
    String slug;

    List<WarrantyResponse> warranties;
    List<AttributeResponse> attributes;
    List<VariantResponse> variants;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class AttributeResponse {
        Long attributeId;
        String attributeName;
        List<OptionResponse> attributeOptions;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @FieldDefaults(level = AccessLevel.PRIVATE)
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public static class OptionResponse {
            Long optionId;
            String optionName;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class WarrantyResponse {
        Long warrantyId;
        String warrantyName;
        Long durationMonths;
        String description;
        BigDecimal price;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class VariantResponse {
        Long variantId;
        String variantName;
        String techInfo;
        BigDecimal sellingPrice;
        BigDecimal priceAfterDiscount;
        Long stock;
        List<Long> optionIds;
        String mainImg;

        List<WarehouseResponse> warehouses;
        List<PromotionResponse> promotions;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @FieldDefaults(level = AccessLevel.PRIVATE)
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public static class WarehouseResponse {
            Long warehouseId;
            String address;
            String phonenumber;
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @FieldDefaults(level = AccessLevel.PRIVATE)
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public static class PromotionResponse {
            Long promotionId;
            String promotionName;
            String note;
            Long type;
            Boolean isPercent;
            BigDecimal discountValue;

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            LocalDateTime startDate;

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            LocalDateTime endDate;

            List<ComplimentProductResponse> complimentProducts;

            @Data
            @Builder
            @NoArgsConstructor
            @AllArgsConstructor
            @FieldDefaults(level = AccessLevel.PRIVATE)
            @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
            public static class ComplimentProductResponse {
                Long id;
                String name;
                BigDecimal sellingPrice;
                BigDecimal priceAfterDiscount;
            }
        }
    }
}
