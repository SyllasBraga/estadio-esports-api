package com.estadioesports.controller;

import com.estadioesports.dtos.GeneroDto;
import com.estadioesports.entities.Genero;
import com.estadioesports.services.GeneroService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @PreAuthorize("permitAll")
    @GetMapping
    public List<Genero> findAll(){
        return generoService.findAll();
    }

    @PreAuthorize("permitAll")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Genero> findById(@PathVariable Long id){
        return generoService.findById(id);
    }

    @PreAuthorize("permitAll")
    @PostMapping
    public ResponseEntity<Genero> save(@RequestBody GeneroDto generoDto){
        Genero genero = new Genero();
        BeanUtils.copyProperties(generoDto, genero);
        return generoService.save(genero);
    }

    @PreAuthorize("permitAll")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Genero> update(@PathVariable Long id, @RequestBody GeneroDto generoDto){
        Genero genero = new Genero();
        BeanUtils.copyProperties(generoDto, genero);
        return generoService.update(id, genero);
    }

    @PreAuthorize("permitAll")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return generoService.delete(id);
    }
}
