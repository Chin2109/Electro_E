package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "promotions")
public class Promotions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "promo_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promoId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private Long type;

    @Column(name = "discount_value", nullable = false)
    private BigDecimal discountValue;

    @Column(name = "is_percent", nullable = false)
    private Boolean percent;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @Column(name = "apply_condition")
    private BigDecimal applyCondition;

    @Column(name = "note")
    private String note;

    @Column(name = "max_usage")
    private Long maxUsage;

    @Column(name = "current_usage", nullable = false)
    private Long currentUsage;

}
