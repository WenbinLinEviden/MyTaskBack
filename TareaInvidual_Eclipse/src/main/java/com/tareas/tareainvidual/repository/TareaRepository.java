package com.tareas.tareainvidual.repository;

import com.tareas.tareainvidual.models.Tarea;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findAllByCalendarioId(Long idCalendario, Sort by);

    List<Tarea> findAllByCalendarioIdOrderByInit(Long idCalendario);

    Tarea findTopByOrderByIdDesc();


}
