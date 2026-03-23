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
@Table(name = "stock_in_item")
public class StockInItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockin_item_id", nullable = false)
    private Long stockinItemId;

    @Column(name = "stockin_id", nullable = false)
    private Long stockinId;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "import_price", nullable = false)
    private BigDecimal importPrice;

}
