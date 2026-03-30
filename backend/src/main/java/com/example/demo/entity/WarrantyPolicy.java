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
@Table(name = "warranty_policy")
public class WarrantyPolicy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "warrant_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warrantId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "duration_months")
    private Long durationMonths;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Long status;

}
