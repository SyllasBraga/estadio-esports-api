package com.estadioesports.dtos;

import com.estadioesports.entities.Administrador;
import com.estadioesports.entities.Jogo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class EventoDto {

    @Size(min = 4)
    private String nomeEvt;
    @NotNull
    private Date dataInicio;
    @NotNull
    private Date dataFim;
    @NotNull
    private Double premiacao;
    @NotNull
    private Boolean exclusivoArena;
    @NotNull
    private Jogo jogo;
    @NotNull
    private Administrador administrador;

}
