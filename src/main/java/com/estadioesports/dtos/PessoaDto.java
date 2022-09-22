package com.estadioesports.dtos;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class PessoaDto {

    @CPF
    private String cpf;
    @Size(min = 3, max = 40)
    private String nome;
    @Size(min = 3, max = 40)
    private String sobrenome;
    private Date dataNascimento;
    @Email
    private String login;
    @Size(min = 8, max = 255)
    private String senha;

}
