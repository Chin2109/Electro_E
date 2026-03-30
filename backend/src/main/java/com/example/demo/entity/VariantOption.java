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
@Table(name = "variant_option")
//BỎ
public class VariantOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "option_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    @Column(name = "attribute_id", nullable = false)
    private Long attributeId;

    @Column(name = "value", nullable = false)
    private String value;

}
