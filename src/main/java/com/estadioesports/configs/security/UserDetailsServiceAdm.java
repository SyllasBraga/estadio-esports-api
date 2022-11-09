package com.estadioesports.configs.security;

import com.estadioesports.entities.Administrador;
import com.estadioesports.repository.AdministradorRepository;
import com.estadioesports.repository.EspectadorRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceAdm implements UserDetailsService {


    final AdministradorRepository admRepository;
    final EspectadorRepository espectadorRepository;

    public UserDetailsServiceAdm(AdministradorRepository admRepository, EspectadorRepository espectadorRepository) {
        this.admRepository = admRepository;
        this.espectadorRepository = espectadorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Administrador adm = admRepository.findByLogin(login)
                .orElseThrow(()-> new UsernameNotFoundException ("Esse login não foi encontrado" + login));

            return new User(adm.getLogin(), adm.getSenha(), true,
             true, true,
             true, adm.getAuthorities());   
    }
}
