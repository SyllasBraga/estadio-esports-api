package com.estadioesports.controller;

import com.estadioesports.dtos.JogoDto;
import com.estadioesports.entities.Jogo;
import com.estadioesports.services.JogoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @PreAuthorize("permitAll")
    @GetMapping
    public List<Jogo> findAll(){
        return jogoService.findAllAll();
    }

    @PreAuthorize("permitAll")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Jogo> findById(@PathVariable Long id){
        return jogoService.findById(id);
    }

    @PreAuthorize("permitAll")
    @PostMapping
    public ResponseEntity<Jogo> save(@RequestBody @Valid JogoDto jogoDto){
        Jogo jogo = new Jogo();
        BeanUtils.copyProperties(jogoDto, jogo);
        return jogoService.create(jogo);
    }

    @PreAuthorize("permitAll")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Jogo> update(@PathVariable Long id, @RequestBody @Valid JogoDto jogoDto){
        Jogo jogo = new Jogo();
        BeanUtils.copyProperties(jogoDto, jogo);
        return jogoService.update(id, jogo);
    }

    @PreAuthorize("permitAll")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return jogoService.delete(id);
    }
}
