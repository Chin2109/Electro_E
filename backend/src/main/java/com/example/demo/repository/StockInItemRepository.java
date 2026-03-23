package com.example.demo.repository;

import com.example.demo.entity.StockInItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StockInItemRepository extends JpaRepository<StockInItem, Long>, JpaSpecificationExecutor<StockInItem> {

}