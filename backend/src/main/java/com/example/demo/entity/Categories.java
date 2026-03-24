package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
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
@Table(name = "categories")
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "category_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private Long status;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpecAttribute> specs;

}
