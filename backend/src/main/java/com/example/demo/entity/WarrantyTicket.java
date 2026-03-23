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
@Table(name = "warranty_ticket")
public class WarrantyTicket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ticket_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @Column(name = "productitem_id", nullable = false)
    private Long productitemId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;

    @Column(name = "issue_description", nullable = false)
    private String issueDescription;

    @Column(name = "status")
    private Long status;

    @Column(name = "received_date")
    private Date receivedDate;

    @Column(name = "expected_return")
    private Date expectedReturn;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "technician_note")
    private String technicianNote;

}
