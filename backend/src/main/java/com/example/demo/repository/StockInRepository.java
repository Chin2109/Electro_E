package com.example.demo.repository;

import com.example.demo.entity.StockIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StockInRepository extends JpaRepository<StockIn, Long>, JpaSpecificationExecutor<StockIn> {

}