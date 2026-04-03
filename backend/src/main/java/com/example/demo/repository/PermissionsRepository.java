package com.example.demo.repository;

import com.example.demo.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionsRepository extends JpaRepository<Permissions,Long>, JpaSpecificationExecutor<Permissions> {


}
