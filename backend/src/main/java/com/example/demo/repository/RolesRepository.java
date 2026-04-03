package com.example.demo.repository;

import com.example.demo.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long>, JpaSpecificationExecutor<Roles> {
    @Query("SELECT r FROM Roles r " +
            "LEFT JOIN RolePermissions rp ON r.roleId = rp.rolePermissionsId.roleId " +
            "LEFT JOIN Permissions p ON rp.rolePermissionsId.permissionId = p.permissionId " +
            "WHERE r.roleId = :roleId")
    Optional<Roles> findRoleWithPermissions(@Param("roleId") Long roleId);
}