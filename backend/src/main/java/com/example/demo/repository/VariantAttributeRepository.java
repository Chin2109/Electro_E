package com.example.demo.repository;

import com.example.demo.entity.VariantAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VariantAttributeRepository extends JpaRepository<VariantAttribute, Long>, JpaSpecificationExecutor<VariantAttribute> {

}