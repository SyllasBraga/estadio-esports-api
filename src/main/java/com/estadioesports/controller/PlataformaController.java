package com.estadioesports.controller;

import com.estadioesports.dtos.PlataformaDto;
import com.estadioesports.entities.Administrador;
import com.estadioesports.entities.Plataforma;
import com.estadioesports.services.PlataformaService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/plataformas"})
public class PlataformaController {

    PlataformaService plataformaService;

    public PlataformaController(PlataformaService plataformaService) {
        this.plataformaService = plataformaService;
    }

    @PreAuthorize("permitAll")
    @GetMapping
    public List<Plataforma> findAll(){
        return plataformaService.findAll();
    }

    @PreAuthorize("permitAll")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Plataforma> findById(@PathVariable Long id){
        return plataformaService.findById(id);
    }

    @PreAuthorize("permitAll")
    @PostMapping
    public ResponseEntity<Plataforma> create(@RequestBody @Valid PlataformaDto plataformaDto){
        Plataforma plataforma = new Plataforma();
        BeanUtils.copyProperties(plataformaDto, plataforma);
        return plataformaService.create(plataforma);
    }

    @PreAuthorize("permitAll")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Plataforma> update(@PathVariable Long id, @RequestBody @Valid PlataformaDto plataformaDto) {
        Plataforma plataforma = new Plataforma();
        BeanUtils.copyProperties(plataformaDto, plataforma);
        return plataformaService.update(id,plataforma);
    }
    @PreAuthorize("permitAll")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return plataformaService.delete(id);
    }

}
