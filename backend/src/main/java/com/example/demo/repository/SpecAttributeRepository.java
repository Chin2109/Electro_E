package com.example.demo.repository;

import com.example.demo.entity.SpecAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpecAttributeRepository extends JpaRepository<SpecAttribute, Long>, JpaSpecificationExecutor<SpecAttribute> {

}