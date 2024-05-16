package com.tareas.tareainvidual.repository;

import com.tareas.tareainvidual.models.Calendario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {
    List<Calendario> findAllByUsuarioId(Long id);
    boolean existsByDiaAndUsuarioId(LocalDate dia, Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM tarea WHERE calendario_id = :calendarioId")
    void deleteTareasByCalendarioId(@PathVariable("calendarioId") Long calendarioId);



}

