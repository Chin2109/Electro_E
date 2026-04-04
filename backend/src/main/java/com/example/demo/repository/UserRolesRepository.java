package com.example.demo.repository;

import com.example.demo.entity.UserRoles;
import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long>, JpaSpecificationExecutor<UserRoles> {

    List<UserRoles> findByUser(Users user);

    List<UserRoles> findAllByUser_UserId(Long userId);
}