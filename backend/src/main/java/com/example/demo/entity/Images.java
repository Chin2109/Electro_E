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
@Table(name = "images")
@Accessors(chain = true)
public class Images implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "image_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "is_thumbnail", nullable = false)
    private Boolean thumbnail;
}
