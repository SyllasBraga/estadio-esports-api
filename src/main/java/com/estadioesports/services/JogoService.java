package com.estadioesports.services;

import com.estadioesports.entities.Jogo;
import com.estadioesports.repository.JogoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    public List<Jogo> findAllAll(){
        return jogoRepository.findAll();
    }

    public ResponseEntity<Jogo> findById(Long id){
        return jogoRepository.findById(id).map(Record -> {
            return ResponseEntity.ok().body(Record);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Jogo> create(Jogo jogo){
        jogoRepository.save(jogo);
        return ResponseEntity.ok().body(jogo);
    }

    public ResponseEntity<Jogo> update(Long id, Jogo jogo){
        return jogoRepository.findById(id).map(Record -> {
          Record.setAdministrador(jogo.getAdministrador());
            Record.setGenero(jogo.getGenero());
            Record.setNomeJogo(jogo.getNomeJogo());
            Record.setPlataforma(jogo.getPlataforma());
            Jogo jogoAtual = jogoRepository.save(Record);
            return ResponseEntity.ok().body(jogoAtual);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> delete(Long id){
        return jogoRepository.findById(id).map(Record -> {
            jogoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
