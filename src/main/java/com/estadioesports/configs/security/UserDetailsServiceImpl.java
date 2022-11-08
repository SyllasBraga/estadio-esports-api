package com.estadioesports.configs.security;

import com.estadioesports.entities.Administrador;
import com.estadioesports.entities.Espectador;
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
public class UserDetailsServiceImpl implements UserDetailsService {


    final AdministradorRepository admRepository;
    final EspectadorRepository espectadorRepository;

    public UserDetailsServiceImpl(AdministradorRepository admRepository, EspectadorRepository espectadorRepository) {
        this.admRepository = admRepository;
        this.espectadorRepository = espectadorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Administrador adm = admRepository.findByLogin(login)
                .orElseThrow(()-> new UsernameNotFoundException ("Esse login não foi encontrado" + login));
        Espectador espectador = espectadorRepository.findByLogin(login)
                .orElseThrow(()-> new UsernameNotFoundException ("Esse login não foi encontrado" + login));
        System.out.println(adm.getNome());
            if (adm.getId() != null) {
            return new User(adm.getLogin(), adm.getSenha(), true,
             true, true,
             true, adm.getAuthorities());   
        } else {
            return new User(espectador.getLogin(), espectador.getSenha(), true,
             true, true,
             true, espectador.getAuthorities());   
        }
              
    }
}
