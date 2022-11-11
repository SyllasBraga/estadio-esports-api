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
public class UserDetailsServiceAdm implements UserDetailsService {

    final AdministradorRepository admRepository;
    final EspectadorRepository espectadorRepository;

    public UserDetailsServiceAdm(AdministradorRepository admRepository, EspectadorRepository espectadorRepository) {
        this.admRepository = admRepository;
        this.espectadorRepository = espectadorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            Administrador adm = admRepository.findByLogin(login).orElseThrow(() -> null);

            if (adm.getId() == null) {
                Espectador espectador = espectadorRepository.findByLogin(login)
                        .orElseThrow(() -> null);

                return new User(espectador.getLogin(), espectador.getSenha(), true,
                        true, true,
                        true, espectador.getAuthorities());
            } else {
                return new User(adm.getLogin(), adm.getSenha(), true,
                        true, true,
                        true, adm.getAuthorities());
            }

        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
