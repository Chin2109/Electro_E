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
@Table(name = "orders")
@Accessors(chain = true)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "stockout_id")
    private Long stockoutId;

    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Column(name = "shipper_id")
    private Long shipperId;

    @Column(name = "created_at_datetime", nullable = false)
    private Date createdAtDatetime;

    @Column(name = "completed_at")
    private Date completedAt;

    @Column(name = "status_order", nullable = false)
    private Long statusOrder;

    @Column(name = "delivery_method")
    private Long deliveryMethod;

    @Column(name = "to_phone")
    private String toPhone;

    @Column(name = "to_name")
    private String toName;

    @Column(name = "to_address")
    private String toAddress;

    @Column(name = "to_ward")
    private String toWard;

    @Column(name = "to_district")
    private String toDistrict;

    @Column(name = "to_province")
    private String toProvince;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "shipping_cost", nullable = false)
    private BigDecimal shippingCost;

    @Column(name = "total_pay", nullable = false)
    private BigDecimal totalPay;

    @Column(name = "payment_method", nullable = false)
    private Long paymentMethod;

    @Column(name = "payment_status", nullable = false)
    private Long paymentStatus;

    @Column(name = "note")
    private String note;

}
