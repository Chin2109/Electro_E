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
@Table(name = "stock_in")
public class StockIn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "stockin_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockinId;

    @Column(name = "supplier_id", nullable = false)
    private Long supplierId;

    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;

    @Column(name = "staff_id", nullable = false)
    private Long staffId;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "status", nullable = false)
    private Long status;

    @Column(name = "import_date", nullable = false)
    private Date importDate;

    @Column(name = "note")
    private String note;

}
