package com.estadioesports.entities;

import com.estadioesports.enuns.RoleName;

import lombok.Data;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
public class Roles implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }
}
