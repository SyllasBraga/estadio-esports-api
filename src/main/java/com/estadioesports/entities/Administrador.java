package com.estadioesports.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Administrador extends Pessoa{

    private double salario;

    @ManyToMany
    @JoinTable(name = "pessoa_roles", joinColumns = @JoinColumn(name = "id_adm"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Roles> roleId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleId;
    }

}
