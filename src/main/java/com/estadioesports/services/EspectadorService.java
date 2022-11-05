package com.estadioesports.services;

import com.estadioesports.entities.Espectador;
import com.estadioesports.repository.EspectadorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EspectadorService {

    EspectadorRepository espectadorRepository;

    public EspectadorService(EspectadorRepository espectadorRepository) {
        this.espectadorRepository = espectadorRepository;
    }

    public List<Espectador> findAll(){
        return espectadorRepository.findAll();
    }

    public ResponseEntity<Espectador> findById(UUID id){
        return espectadorRepository.findById(id).map( Record -> {
            return ResponseEntity.ok().body(Record);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Espectador> save(Espectador espectador){
        espectador.setSenha(passwordEncoder().encode(espectador.getSenha()));
        espectadorRepository.save(espectador);
        return ResponseEntity.ok().body(espectador);
    }

    public ResponseEntity<Espectador> update(UUID id, Espectador espectador){
        return espectadorRepository.findById(id).map(Record -> {
            Record.setNome(espectador.getNome());
            Record.setCpf(espectador.getCpf());
            Record.setLogin(espectador.getLogin());
            Record.setSenha(passwordEncoder().encode(espectador.getSenha()));
            Record.setDataNascimento(espectador.getDataNascimento());
            Record.setSobrenome(espectador.getSobrenome());
            Espectador espectadorNovo = espectadorRepository.save(Record);
            return ResponseEntity.ok().body(espectadorNovo);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> delete(UUID id){
        return espectadorRepository.findById(id).map(Record -> {
            espectadorRepository.deleteById(Record.getId());
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
