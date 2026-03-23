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
@Table(name = "brands")
@Accessors(chain = true)
public class Brands implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "brand_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url_image")
    private String urlImage;

    @Column(name = "description")
    private String description;

}
