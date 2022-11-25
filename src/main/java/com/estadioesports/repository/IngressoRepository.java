package com.estadioesports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estadioesports.entities.Ingresso;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long>{
    
}
