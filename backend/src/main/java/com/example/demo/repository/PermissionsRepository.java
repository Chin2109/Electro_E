package com.example.demo.repository;

import com.example.demo.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionsRepository extends JpaRepository<Permissions,Long>, JpaSpecificationExecutor<Permissions> {

    @Query("SELECT p FROM Permissions p " +
            "JOIN RolePermissions rp ON p.permissionId = rp.rolePermissionsId.permissionId " +
            "WHERE rp.rolePermissionsId.roleId = :roleId")
    List<Permissions> findAllByRoleId(@Param("roleId") Long roleId);
}
