package com.estadioesports.services;

import com.estadioesports.entities.Evento;
import com.estadioesports.repository.EventoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> findAll(){
        return eventoRepository.findAll();
    }

    public ResponseEntity<Evento> findById(Long id){
        return eventoRepository.findById(id).map( Record -> {
            return ResponseEntity.ok().body(Record);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Evento> create(Evento evento){
        eventoRepository.save(evento);
        return ResponseEntity.ok().body(evento);
    }

    public ResponseEntity<Evento> update(Long id, Evento evento){
        return eventoRepository.findById(id).map(Record -> {
            Record.setNomeEvt(evento.getNomeEvt());
            Record.setPremiacao(evento.getPremiacao());
            Record.setExclusivoArena(evento.getExclusivoArena());
            Record.setDataInicio(evento.getDataInicio());
            Record.setDataFim(evento.getDataFim());
            Record.setJogo(evento.getJogo());
            Record.setAdministrador(evento.getAdministrador());
            Evento eventoAtual = eventoRepository.save(Record);
            return ResponseEntity.ok().body(eventoAtual);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> delete(Long id){
        return eventoRepository.findById(id).map(Record -> {
            eventoRepository.deleteById(Record.getId());
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
