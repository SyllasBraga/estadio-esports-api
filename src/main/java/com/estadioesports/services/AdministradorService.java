package com.estadioesports.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.estadioesports.entities.Administrador;
import com.estadioesports.repository.AdministradorRepository;

@Service
public class AdministradorService {
    
    AdministradorRepository admRepository;

    public AdministradorService(AdministradorRepository admRepository) {
        this.admRepository = admRepository;
    }

    public List<Administrador> findAll(){
        return admRepository.findAll();
    }

    public ResponseEntity<Administrador> findById(long id){
        return admRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public Administrador create(Administrador adm){
        return admRepository.save(adm);
    }

    public ResponseEntity<Administrador>update(long id, Administrador adm){
        return admRepository.findById(id).map(Record->{
            Record.setIdade(adm.getIdade());
            Record.setLogin(adm.getLogin());
            Record.setNome(adm.getNome());
            Record.setSalario(adm.getSalario());
            Record.setSenha(adm.getSenha());
            Administrador atual = admRepository.save(Record);
            return ResponseEntity.ok().body(atual);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> delete(long id){
        return admRepository.findById(id).map(Record -> {
            admRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse( ResponseEntity.notFound().build());
    }
}
