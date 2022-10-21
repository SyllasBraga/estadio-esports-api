package com.estadioesports.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeEvt;
    private Date dataInicio;
    private Date dataFim;
    private Double premiacao;
    private Boolean exclusivoArena;

    @ManyToOne
    @JoinColumn(name = "cod_jogo")
    private Jogo jogo;

    @ManyToOne
    @JoinColumn(name = "cod_adm")
    private Administrador administrador;
}
