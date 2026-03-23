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
public class ProductSpecAttributeId {
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "attribute_id", nullable = false)
    private Long attributeId;
}
