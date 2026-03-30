package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductVariantOptionId {
    @Column(name = "variant_id", nullable = false)
    private Long variantId; //tham chiếu đến ProductVariant

    @Column(name = "attribute_id", nullable = false)
    private Long attributeId;
}
