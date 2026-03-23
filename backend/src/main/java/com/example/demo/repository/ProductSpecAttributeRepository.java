package com.example.demo.repository;

import com.example.demo.entity.ProductSpecAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductSpecAttributeRepository extends JpaRepository<ProductSpecAttribute, Long>, JpaSpecificationExecutor<ProductSpecAttribute> {

}