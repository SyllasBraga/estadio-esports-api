package com.estadioesports.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class Espectador extends Pessoa{

    @ManyToMany
    @JoinTable(name = "pessoa_roles", joinColumns = @JoinColumn(name = "id_espectador"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Roles> roleId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleId;
    }
}
