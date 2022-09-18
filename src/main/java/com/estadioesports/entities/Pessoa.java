package com.estadioesports.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
@Valid
public class Pessoa implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type = "uuid-char")
    private UUID id;
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

    @ManyToMany
    @JoinTable(name = "pessoa_roles", joinColumns = @JoinColumn(name = "id_pessoa"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Roles> roleId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleId;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
