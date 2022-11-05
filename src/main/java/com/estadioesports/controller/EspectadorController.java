package com.estadioesports.controller;

import com.estadioesports.dtos.PessoaDto;
import com.estadioesports.entities.Espectador;
import com.estadioesports.services.EspectadorService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/espectadores")
public class EspectadorController {

    EspectadorService espectadorService;

    public EspectadorController(EspectadorService espectadorService) {
        this.espectadorService = espectadorService;
    }

    @PreAuthorize("permitAll")
    @GetMapping
    public List<Espectador> findAll(){
        return espectadorService.findAll();
    }

    @PreAuthorize("permitAll")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Espectador> findById(@PathVariable UUID id){
        return espectadorService.findById(id);
    }

    @PreAuthorize("permitAll")
    @PostMapping
    public ResponseEntity<Espectador> save(@RequestBody PessoaDto pessoaDto){
        Espectador espectador = new Espectador();
        BeanUtils.copyProperties(pessoaDto, espectador);
        return espectadorService.save(espectador);
    }

    @PreAuthorize("permitAll")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Espectador> update(@PathVariable UUID id, @RequestBody PessoaDto pessoaDto){
        Espectador espectador = new Espectador();
        BeanUtils.copyProperties(pessoaDto, espectador);
        return espectadorService.update(id, espectador);
    }

    @PreAuthorize("permitAll")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        return espectadorService.delete(id);
    }
}
