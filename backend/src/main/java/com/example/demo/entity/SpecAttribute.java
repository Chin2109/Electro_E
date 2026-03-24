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
@ToString(exclude = "category")
@SuperBuilder
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "spec_attribute")
public class SpecAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "attribute_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attributeId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "unit")
    private String unit;

    @Column(name = "status", nullable = false)
    private Long status;

}
