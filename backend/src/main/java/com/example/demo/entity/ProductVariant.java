package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * $table.getTableComment()
 */
@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "product_variant")
public class ProductVariant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "variant_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long variantId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "status", nullable = false)
    private Long status;

    @Column(name = "buying_price", nullable = false)
    private BigDecimal buyingPrice;

    @Column(name = "selling_price", nullable = false)
    private BigDecimal sellingPrice;

    @Column(name = "stock_quantity", nullable = false)
    private Long stockQuantity;

    @Column(name = "sold_quantity", nullable = false)
    private Long soldQuantity;

    @Column(name = "sku")
    private String sku;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductVariantOption> options;

}
