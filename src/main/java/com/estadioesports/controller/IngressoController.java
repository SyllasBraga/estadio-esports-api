package com.estadioesports.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estadioesports.entities.Ingresso;
import com.estadioesports.services.IngressoService;

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
}
