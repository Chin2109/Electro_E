package com.example.demo.repository;

import com.example.demo.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long>, JpaSpecificationExecutor<UserRoles> {

}