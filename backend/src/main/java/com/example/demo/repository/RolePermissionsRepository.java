package com.example.demo.repository;

import com.example.demo.entity.RolePermissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RolePermissionsRepository extends JpaRepository<RolePermissions,Long>, JpaSpecificationExecutor<RolePermissions> {
}
