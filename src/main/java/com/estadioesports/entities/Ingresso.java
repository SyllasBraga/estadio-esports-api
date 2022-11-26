package com.estadioesports.entities;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Ingresso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int estoque;
    private double valor;
    private Date validade;

    @ManyToOne
    @JoinColumn(name = "evento")
    private Evento evento;

}
