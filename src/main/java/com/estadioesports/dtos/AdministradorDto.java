package com.estadioesports.dtos;

import javax.validation.constraints.DecimalMin;

import lombok.Data;

@Data
public class AdministradorDto extends PessoaDto{
    
    @DecimalMin("1212.0")
    private double salario;
    
}
