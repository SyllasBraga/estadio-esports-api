package com.estadioesports.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.estadioesports.entities.Ingresso;
import com.estadioesports.services.IngressoService;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {
    
    IngressoService ingressoService;

    public IngressoController(IngressoService ingressoService) {
        this.ingressoService = ingressoService;
    }

    @GetMapping
    @PreAuthorize("permitAll")
    public List<Ingresso> findAll(){
        return ingressoService.findAll();
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("permitAll")
    public ResponseEntity<Ingresso> findById(@PathVariable Long id){
        return ingressoService.findById(id);
    }
    @PostMapping
    @PreAuthorize("permitAll")
    public ResponseEntity<Ingresso> create(@RequestBody Ingresso ingresso){
        return ingressoService.create(ingresso);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("permitAll")
    public ResponseEntity<Ingresso> update(@PathVariable Long id, @RequestBody Ingresso ingresso){
        return ingressoService.update(id, ingresso);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("permitAll")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ingressoService.delete(id);
    }

}
