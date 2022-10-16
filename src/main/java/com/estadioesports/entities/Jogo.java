package com.estadioesports.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeJogo;

    @ManyToOne
    @JoinColumn(name = "plataforma")
    private Plataforma plataforma;

    @ManyToOne
    @JoinColumn(name = "genero")
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "adm")
    private Administrador administrador;
}
