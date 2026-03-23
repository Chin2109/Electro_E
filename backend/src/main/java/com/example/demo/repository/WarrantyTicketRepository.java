package com.example.demo.repository;

import com.example.demo.entity.WarrantyTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WarrantyTicketRepository extends JpaRepository<WarrantyTicket, Long>, JpaSpecificationExecutor<WarrantyTicket> {

}