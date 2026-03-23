package com.example.demo.repository;

import com.example.demo.entity.WarrantyPolicyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WarrantyPolicyProductRepository extends JpaRepository<WarrantyPolicyProduct, Long>, JpaSpecificationExecutor<WarrantyPolicyProduct> {

}