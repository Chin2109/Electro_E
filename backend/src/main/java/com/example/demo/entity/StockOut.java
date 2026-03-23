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
@Table(name = "stock_out")
public class StockOut implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "stockout_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockoutId;

    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "staff_id", nullable = false)
    private Long staffId;

    @Column(name = "export_date", nullable = false)
    private Date exportDate;

    @Column(name = "type_of_export", nullable = false)
    private Long typeOfExport;

    @Column(name = "total_quantity")
    private Long totalQuantity;

    @Column(name = "note")
    private String note;

    @Column(name = "status", nullable = false)
    private Long status;

}
