package com.estadioesports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estadioesports.entities.Roles;

@Repository
public interface RolesRepository extends JpaRepository <Roles, Long> {
}
