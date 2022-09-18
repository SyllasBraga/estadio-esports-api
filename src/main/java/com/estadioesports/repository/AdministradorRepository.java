package com.estadioesports.repository;

import com.estadioesports.entities.Administrador;
import org.apache.logging.log4j.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, UUID> {
    Optional<Administrador> findByLogin(String login);
}
