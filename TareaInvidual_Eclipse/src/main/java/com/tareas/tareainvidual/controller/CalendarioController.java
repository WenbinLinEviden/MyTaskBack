package com.tareas.tareainvidual.controller;

import com.tareas.tareainvidual.models.Calendario;
import com.tareas.tareainvidual.models.Tarea;
import com.tareas.tareainvidual.models.dto.CalendarioDTO;
import com.tareas.tareainvidual.repository.CalendarioRepository;
import com.tareas.tareainvidual.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{id_usuario}")
public class CalendarioController {

    @Autowired
    private CalendarioRepository calendarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/calendario")
    public ResponseEntity<List<CalendarioDTO>> getCalendario(@PathVariable("id_usuario") Long id_usuario) {
        List<Calendario> calendarios = calendarioRepository.findAllByUsuarioId(id_usuario);
        List<CalendarioDTO> calendarioDTOLista = new ArrayList<>();
        for (Calendario calendario : calendarios) {
            CalendarioDTO calendarioDTO = new CalendarioDTO();
            calendarioDTO.setId(calendario.getId());
            calendarioDTO.setDia(calendario.getDia());
            calendarioDTO.setUsuario(calendario.getUsuario().getId());
            calendarioDTOLista.add(calendarioDTO);
        }
        return ResponseEntity.ok(calendarioDTOLista);
    }

    @GetMapping("/{id_calendario}")
    public ResponseEntity<CalendarioDTO> getCalendarioById(@PathVariable("id_calendario") Long id_calendario) {
        Calendario calendario = calendarioRepository.findById(id_calendario).orElse(null);
        if (calendario != null) {
            CalendarioDTO calendarioDTO = new CalendarioDTO();
            calendarioDTO.setId(calendario.getId());
            calendarioDTO.setDia(calendario.getDia());
            calendarioDTO.setUsuario(calendario.getUsuario().getId());
            
            return ResponseEntity.ok(calendarioDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Calendario> createCalendario(@PathVariable("id_usuario") Long id_usuario,@RequestBody CalendarioDTO calendarioDTO) {
        if (calendarioRepository.existsByDiaAndUsuarioId(calendarioDTO.getDia(), id_usuario)) {
            return ResponseEntity.badRequest().build();
        }else {
        	Calendario calendario = new Calendario();
        	calendario.setDia(calendarioDTO.getDia());
        	calendario.setUsuario(this.usuarioRepository.findById(id_usuario).orElse(null));
        
             Calendario newCalendario = calendarioRepository.save(calendario);
            return ResponseEntity.ok(newCalendario);
        }
    }

    //da error, pero funciona correctamente
    @DeleteMapping("/{id_calendario}/delete")
    public ResponseEntity<Calendario> deleteCalendario(@PathVariable("id_calendario") Long id_calendario) {
        Calendario calendario = calendarioRepository.findById(id_calendario).orElse(null);
        if (calendario != null) {
            calendarioRepository.deleteTareasByCalendarioId(id_calendario);
            calendarioRepository.delete(calendario);
            return ResponseEntity.ok(calendario);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
