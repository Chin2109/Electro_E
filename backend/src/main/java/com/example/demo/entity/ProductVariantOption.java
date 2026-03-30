package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

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
@Table(name = "product_variant_option")
public class ProductVariantOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ProductVariantOptionId id;

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne
    @MapsId("variantId")
    @JoinColumn(name = "variant_id")
    @JsonIgnore
    private ProductVariant variant;

}
