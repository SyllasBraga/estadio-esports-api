package com.estadioesports.configs.validation;

public class ValidaSenha {

    public boolean validaTamanhoSenha(String senha){
        if (senha.length() < 8){
            return false;
        }else{
            return true;
        }
    }
}
