package com.estadioesports.configs.security;

import com.estadioesports.entities.Administrador;
import com.estadioesports.repository.AdministradorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    final AdministradorRepository admRepository;

    public UserDetailsServiceImpl(AdministradorRepository admRepository) {
        this.admRepository = admRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Administrador adm = admRepository.findByLogin(login)
                .orElseThrow(()-> new UsernameNotFoundException("Esse login não foi encontrado" + login));
        return adm;
    }
}
