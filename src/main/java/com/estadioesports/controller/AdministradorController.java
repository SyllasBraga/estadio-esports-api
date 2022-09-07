package com.estadioesports.controller;


import com.estadioesports.entities.Administrador;
import com.estadioesports.services.AdministradorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/administradores"})
public class AdministradorController {

    AdministradorService admService;

    public AdministradorController(AdministradorService admService) {
        this.admService = admService;
    }

    @GetMapping
    public List<Administrador> findAll(){
        return admService.findAll();
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Administrador> findById(@PathVariable long id){
        return admService.findById(id);
    }

    @PostMapping
    public Administrador create(@RequestBody Administrador adm){
        return admService.create(adm);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Administrador> update(@PathVariable("id") long id, @RequestBody Administrador adm){
        return admService.update(id, adm);
    }   
    
    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        return admService.delete(id);
    }

}
