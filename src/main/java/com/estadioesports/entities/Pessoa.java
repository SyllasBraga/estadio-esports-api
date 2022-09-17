package com.estadioesports.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
    public class Pessoa implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @org.hibernate.annotations.Type(type="uuid-char")
        private UUID id;

        private String cpf;
        private String nome;
        private Date dataNascimento;
        private String login;
        private String senha;

        @ManyToMany
        @JoinTable(name = "pessoa_roles", joinColumns = @JoinColumn(name = "id_pessoa"),
        inverseJoinColumns = @JoinColumn(name="id_role"))
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
