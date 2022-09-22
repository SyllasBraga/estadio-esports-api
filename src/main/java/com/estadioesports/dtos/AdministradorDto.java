package com.estadioesports.dtos;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class AdministradorDto extends PessoaDto{
    
    @DecimalMin("1212.0")
    private double salario;
    
}
