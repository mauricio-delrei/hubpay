package com.microservices.auth.repository;

import com.microservices.auth.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PermissionRepository extends JpaRepository<Permission, Long>{

    @Query("SELECT p FROM Permission p WHERE p.description =:description")
    Permission findByDescription(@Param("description") String description);
}
