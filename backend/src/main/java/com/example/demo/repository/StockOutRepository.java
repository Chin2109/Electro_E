package com.example.demo.repository;

import com.example.demo.entity.StockOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StockOutRepository extends JpaRepository<StockOut, Long>, JpaSpecificationExecutor<StockOut> {

}