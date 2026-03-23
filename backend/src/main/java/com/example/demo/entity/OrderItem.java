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
@Table(name = "order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "order_item_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "promo_id")
    private Long promoId;

    @Column(name = "unit_price", nullable = false)
    //Giá bán của sản phẩm tại thời điểm mua
    private BigDecimal unitPrice;

    @Column(name = "cost_price", nullable = false)
    //Giá vốn (giá nhập) tại thời điểm mua
    private BigDecimal costPrice;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    @Column(name = "total")
    private BigDecimal total;

}
