package com.example.demo.repository;

import com.example.demo.entity.StockOutItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StockOutItemRepository extends JpaRepository<StockOutItem, Long>, JpaSpecificationExecutor<StockOutItem> {

}