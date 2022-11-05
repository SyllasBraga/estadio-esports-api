package com.estadioesports.repository;

import com.estadioesports.entities.Espectador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EspectadorRepository extends JpaRepository<Espectador, UUID> {
}
