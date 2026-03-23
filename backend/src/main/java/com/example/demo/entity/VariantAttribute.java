package com.example.demo.entity;

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
@Table(name = "variant_attribute")
public class VariantAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "attribute_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attributeId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

}
