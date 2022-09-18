package com.estadioesports.controller;


import com.estadioesports.entities.Administrador;
import com.estadioesports.services.AdministradorService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping({"/administradores"})
public class AdministradorController {

    AdministradorService admService;

    public AdministradorController(AdministradorService admService) {
        this.admService = admService;
    }

    @PreAuthorize("permitAll")
    @GetMapping
    public List<Administrador> findAll(){
         return admService.findAll();
    }

    @PreAuthorize("permitAll")
    @GetMapping(path="/{id}")
    public ResponseEntity<Administrador> findById(@PathVariable UUID id){
        return admService.findById(id);
    }

    @PreAuthorize("permitAll")
    @PostMapping
    public ResponseEntity<Administrador> create(@RequestBody Administrador adm){
        return admService.create(adm);
    }

    @PreAuthorize("permitAll")
    @PutMapping(path="/{id}")
    public ResponseEntity<Administrador> update(@PathVariable("id") UUID id, @RequestBody Administrador adm){
        return admService.update(id, adm);
    }

    @PreAuthorize("permitAll")
    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id){
        return admService.delete(id);
    }

}
