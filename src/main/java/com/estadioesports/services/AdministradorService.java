package com.estadioesports.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.estadioesports.entities.Administrador;
import com.estadioesports.repository.AdministradorRepository;

@Service
public class AdministradorService {

    AdministradorRepository admRepository;

    public AdministradorService(AdministradorRepository admRepository) {
        this.admRepository = admRepository;
    }

    public List<Administrador> findAll() {
        return admRepository.findAll();
    }

    public ResponseEntity<Administrador> findById(UUID id) {
        return admRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Administrador> create(Administrador adm) {
        adm.setSenha(passwordEncoder().encode(adm.getSenha()));
        admRepository.save(adm);
        return ResponseEntity.ok().body(adm);
    }

    public ResponseEntity<Administrador> update(UUID id, Administrador adm) {
        return admRepository.findById(id).map(Record -> {
            Record.setDataNascimento(adm.getDataNascimento());
            Record.setCpf(adm.getCpf());
            Record.setLogin(adm.getLogin());
            Record.setNome(adm.getNome());
            Record.setSobrenome(adm.getSobrenome());
            Record.setSalario(adm.getSalario());
            Record.setSenha(passwordEncoder().encode(adm.getSenha()));
            Administrador atual = admRepository.save(Record);
            return ResponseEntity.ok().body(atual);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> delete(UUID id) {
        return admRepository.findById(id).map(Record -> {
            admRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
