package com.estadioesports.dtos;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

@Data
public class PlataformaDto {

    @Size(min = 3, max = 40)
    private String nomePlataforma;
}
