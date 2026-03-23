package com.example.demo.repository;

import com.example.demo.entity.PromotionsProductvariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PromotionsProductvariantRepository extends JpaRepository<PromotionsProductvariant, Long>, JpaSpecificationExecutor<PromotionsProductvariant> {

}