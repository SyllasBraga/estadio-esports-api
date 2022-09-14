package com.estadioesports.controller;


import com.estadioesports.entities.Administrador;
import com.estadioesports.services.AdministradorService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/administradores"})
public class AdministradorController {

    AdministradorService admService;

    public AdministradorController(AdministradorService admService) {
        this.admService = admService;
    }

    @PreAuthorize("hasRole('administrador')")
    @GetMapping
    public List<Administrador> findAll(){
        return admService.findAll();
    }

    @PreAuthorize("hasRole('administrador')")
    @GetMapping(path="/{id}")
    public ResponseEntity<Administrador> findById(@PathVariable long id){
        return admService.findById(id);
    }

    @PreAuthorize("hasRole('administrador')")
    @PostMapping
    public Administrador create(@RequestBody Administrador adm){
        return admService.create(adm);
    }

    @PreAuthorize("hasRole('administrador')")
    @PutMapping(path="/{id}")
    public ResponseEntity<Administrador> update(@PathVariable("id") long id, @RequestBody Administrador adm){
        return admService.update(id, adm);
    }   
    
    @PreAuthorize("hasRole('administrador')")
    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        return admService.delete(id);
    }

}
