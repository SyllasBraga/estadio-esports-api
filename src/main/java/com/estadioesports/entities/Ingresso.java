package com.estadioesports.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Ingresso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int estoque;
    private double valor;
    private Date validade;

    @ManyToOne
    @JoinTable(name = "evento", joinColumns = @JoinColumn(name = "id"),
    inverseJoinColumns = @JoinColumn(name = "evento"))
    private Evento evento;

}
