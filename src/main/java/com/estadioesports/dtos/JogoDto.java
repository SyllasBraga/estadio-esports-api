package com.estadioesports.dtos;

import com.estadioesports.entities.Administrador;
import com.estadioesports.entities.Genero;
import com.estadioesports.entities.Plataforma;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class JogoDto {

    @Size(min = 3)
    private String nomeJogo;
    @NotNull
    private Plataforma plataforma;
    @NotNull
    private Genero genero;
    @NotNull
    private Administrador administrador;
}
