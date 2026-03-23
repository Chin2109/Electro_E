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
@Table(name = "preorder")
public class Preorder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "preorder_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preorderId;

    @Column(name = "variant_id", nullable = false)
    private Long variantId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "status", nullable = false)
    private Long status;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "note")
    private String note;

}
