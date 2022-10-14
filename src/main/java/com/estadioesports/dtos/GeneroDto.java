package com.estadioesports.dtos;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class GeneroDto {

    @Size(min = 3, max = 40)
    private String nomeGen;

}
