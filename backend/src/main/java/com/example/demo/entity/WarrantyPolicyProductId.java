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
public class WarrantyPolicyProductId {
    @Column(name = "warranty_id", nullable = false)
    private Long warrantyId;

    @Column(name = "product_id", nullable = false)
    private Long productId;
}
