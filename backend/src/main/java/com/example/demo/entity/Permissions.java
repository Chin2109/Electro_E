package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "permissions")
public class Permissions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "permission_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "resource", nullable = false)
    private String resource;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Date createdAt;

}
