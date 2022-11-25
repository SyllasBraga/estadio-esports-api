package com.estadioesports.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.estadioesports.entities.Ingresso;
import com.estadioesports.repository.IngressoRepository;

@Service
public class IngressoService {
    
    IngressoRepository ingressoRepository;

    public IngressoService(IngressoRepository ingressoRepository) {
        this.ingressoRepository = ingressoRepository;
    }

    public List<Ingresso> findAll(){
        return ingressoRepository.findAll();
    }

    public ResponseEntity<Ingresso> findById(Long id){
        return ingressoRepository.findById(id).map(record -> {
            return ResponseEntity.ok().body(record);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Ingresso> create(Ingresso ingresso){
        ingressoRepository.save(ingresso);
        return ResponseEntity.ok().body(ingresso);
    }

    public ResponseEntity<Ingresso> update(Long id, Ingresso ingresso){
        return ingressoRepository.findById(id).map(record -> {
            record.setEstoque(ingresso.getEstoque());
            record.setEvento(ingresso.getEvento());
            record.setValidade(ingresso.getValidade());
            record.setValor(ingresso.getValor());
            return ResponseEntity.ok().body(record);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> delete(Long id){
        return ingressoRepository.findById(id).map(record -> {
            ingressoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
