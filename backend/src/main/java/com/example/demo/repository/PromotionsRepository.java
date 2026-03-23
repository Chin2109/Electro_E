package com.example.demo.repository;

import com.example.demo.entity.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PromotionsRepository extends JpaRepository<Promotions, Long>, JpaSpecificationExecutor<Promotions> {

}