package com.tareas.tareainvidual.controller;

import com.tareas.tareainvidual.models.Tarea;
import com.tareas.tareainvidual.models.dto.TareaDTO;
import com.tareas.tareainvidual.repository.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tareas.tareainvidual.repository.TareaRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{id_usuario}/{id_calendario}")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;
    @Autowired
    private CalendarioRepository calendarioRepository;

    @GetMapping("/tarea")
    public ResponseEntity<List<TareaDTO>> getTarea(@PathVariable("id_calendario") Long id_calendario) {
        List<Tarea> tareas = tareaRepository.findAllByCalendarioId(id_calendario, Sort.by(Sort.Direction.ASC, "init", "ended"));
        List<TareaDTO> tareaDTOLista = new ArrayList<>();
        for (Tarea tarea : tareas) {
            TareaDTO tareaDTO = new TareaDTO();
            tareaDTO.setId(tarea.getId());
            tareaDTO.setDescripcion(tarea.getDescripcion());
            tareaDTO.setInit(tarea.getInit().toString());
            tareaDTO.setEnded(tarea.getEnded().toString());
            tareaDTO.setDescanso(tarea.isDescanso());
            tareaDTO.setCalendario(tarea.getCalendario().getId());
            tareaDTOLista.add(tareaDTO);
        }
        return ResponseEntity.ok(tareaDTOLista);
    }

    @GetMapping("/{id_tarea}")
    public ResponseEntity<TareaDTO> getTareaById(@PathVariable("id_tarea") Long id_tarea) {
        Tarea tarea = tareaRepository.findById(id_tarea).orElse(null);
        if (tarea != null) {
            TareaDTO tareaDTO = new TareaDTO();
            tareaDTO.setId(tarea.getId());
            tareaDTO.setDescripcion(tarea.getDescripcion());
            tareaDTO.setInit(tarea.getInit().toString());
            tareaDTO.setEnded(tarea.getEnded().toString());
            tareaDTO.setDescanso(tarea.isDescanso());
            tareaDTO.setCalendario(tarea.getCalendario().getId());
            
            return ResponseEntity.ok(tareaDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //da error, pero funciona correctamente
    @PostMapping("/new")
    public ResponseEntity<Tarea> createTarea(@PathVariable("id_calendario") Long id_calendario,@RequestBody TareaDTO tareaDTO) {
        Tarea tarea = new Tarea();
        tarea.setInit(LocalTime.parse(tareaDTO.getInit()));
        tarea.setEnded(LocalTime.parse(tareaDTO.getEnded()));
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setDescanso(tareaDTO.isDescanso());
        tarea.setCalendario(this.calendarioRepository.findById(id_calendario).orElse(null));

    	Tarea newTarea = tareaRepository.save(tarea);
        return ResponseEntity.ok(newTarea);
    }

    //da error, pero funciona correctamente
    @DeleteMapping("/{id_tarea}/delete")
    public ResponseEntity<Tarea> deleteTarea(@PathVariable("id_tarea") Long id_tarea){
        Tarea tarea = tareaRepository.findById(id_tarea).orElse(null);
        if (tarea != null) {
            tareaRepository.delete(tarea);
            return ResponseEntity.ok(tarea);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //da error, pero funciona correctamente
    @PutMapping("/{id_tarea}/update")
    public ResponseEntity<Tarea> updateTarea(@PathVariable("id_calendario") Long id_calendario, @PathVariable("id_tarea") Long id_tarea, @RequestBody TareaDTO tareaDTO) {
        Tarea tarea = tareaRepository.findById(id_tarea).orElse(null);
        if (tarea != null) {
            tarea.setInit(LocalTime.parse(tareaDTO.getInit()));
            tarea.setEnded(LocalTime.parse(tareaDTO.getEnded()));
            tarea.setDescripcion(tareaDTO.getDescripcion());
            tarea.setDescanso(tareaDTO.isDescanso());
            tarea.setCalendario(this.calendarioRepository.findById(id_calendario).orElse(null));
            tareaRepository.save(tarea);
            return ResponseEntity.ok(tarea);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tareaOrdenada")
    public ResponseEntity<List<TareaDTO>> getTareaOrdenada(@PathVariable("id_calendario") Long id_calendario) {
        List<Tarea> tareas = tareaRepository.findAllByCalendarioId(id_calendario, Sort.by(Sort.Direction.ASC, "init", "ended"));
        List<TareaDTO> tareaDTOLista = new ArrayList<>();
        for (Tarea tarea : tareas) {
            TareaDTO tareaDTO = new TareaDTO();
            tareaDTO.setId(tarea.getId());
            tareaDTO.setDescripcion(tarea.getDescripcion());
            tareaDTO.setInit(tarea.getInit().toString());
            tareaDTO.setEnded(tarea.getEnded().toString());
            tareaDTO.setDescanso(tarea.isDescanso());
            tareaDTO.setCalendario(tarea.getCalendario().getId());
            tareaDTOLista.add(tareaDTO);
        }
        return ResponseEntity.ok(tareaDTOLista);

    }
}
