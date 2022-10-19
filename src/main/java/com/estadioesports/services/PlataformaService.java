package com.estadioesports.services;

import com.estadioesports.entities.Plataforma;
import com.estadioesports.repository.PlataformaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlataformaService {

    PlataformaRepository plataformaRepository;

    public PlataformaService(PlataformaRepository plataformaRepository) {
        this.plataformaRepository = plataformaRepository;
    }

    public List<Plataforma> findAll(){
        return plataformaRepository.findAll();
    }

    public ResponseEntity<Plataforma> findById(Long id){
        return plataformaRepository.findById(id).map(Record -> ResponseEntity.ok().body(Record))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Plataforma> create(Plataforma plataforma){
        plataformaRepository.save(plataforma);
        return ResponseEntity.ok().body(plataforma);
    }

    public ResponseEntity<Plataforma> update(Long id, Plataforma plataforma) {
        return plataformaRepository.findById(id).map(Record -> {
            Record.setNomePlataforma(plataforma.getNomePlataforma());
            Plataforma atualPlat = plataformaRepository.save(Record);
            return ResponseEntity.ok().body(atualPlat);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> delete(Long id){
        return plataformaRepository.findById(id).map( Record -> {
            plataformaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
