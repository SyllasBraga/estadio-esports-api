package com.estadioesports.controller;

import com.estadioesports.dtos.EventoDto;
import com.estadioesports.entities.Evento;
import com.estadioesports.services.EventoService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PreAuthorize("permitAll")
    @GetMapping
    public List<Evento> findAll(){
        return eventoService.findAll();
    }

    @PreAuthorize("permitAll")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Evento> findById(@PathVariable Long id){
        return eventoService.findById(id);
    }

    @PreAuthorize("permitAll")
    @PostMapping
    public ResponseEntity<Evento> save(@RequestBody EventoDto eventoDto){
        Evento evento = new Evento();
        BeanUtils.copyProperties(eventoDto, evento);
        return eventoService.create(evento);
    }

    @PreAuthorize("permitAll")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Evento> update(@PathVariable Long id, @RequestBody EventoDto eventoDto){
        Evento evento = new Evento();
        BeanUtils.copyProperties(eventoDto, evento);
        return eventoService.update(id, evento);
    }

    @PreAuthorize("permitAll")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return eventoService.delete(id);
    }
}
