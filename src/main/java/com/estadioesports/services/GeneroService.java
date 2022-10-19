package com.estadioesports.services;

import com.estadioesports.entities.Genero;
import com.estadioesports.repository.GeneroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public List<Genero> findAll(){
        return generoRepository.findAll();
    }

    public ResponseEntity<Genero> findById(Long id){
        return generoRepository.findById(id).map(Record -> {
            return ResponseEntity.ok().body(Record);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Genero> save(Genero genero){
        generoRepository.save(genero);
        return ResponseEntity.ok().body(genero);
    }

    public ResponseEntity<Genero> update(Long id, Genero genero){
        return generoRepository.findById(id).map(Record -> {
            Record.setNomeGen(genero.getNomeGen());
            Genero genAtual = generoRepository.save(Record);
            return ResponseEntity.ok().body(genAtual);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> delete(Long id){
        return generoRepository.findById(id).map(Record -> {
            generoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
