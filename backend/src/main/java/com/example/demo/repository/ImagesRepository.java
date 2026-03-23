package com.example.demo.repository;

import com.example.demo.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImagesRepository extends JpaRepository<Images, Long>, JpaSpecificationExecutor<Images> {

}