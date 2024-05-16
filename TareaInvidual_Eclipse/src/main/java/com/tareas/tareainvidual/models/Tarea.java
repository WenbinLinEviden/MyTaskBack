package com.tareas.tareainvidual.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalTime init;
    @Column(nullable = false)
    private LocalTime ended;
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private boolean descanso;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendario_id", nullable = false)
    private Calendario calendario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getInit() {
		return init;
	}

	public void setInit(LocalTime init) {
		this.init = init;
	}

	public LocalTime getEnded() {
		return ended;
	}

	public void setEnded(LocalTime ended) {
		this.ended = ended;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isDescanso() {
		return descanso;
	}

	public void setDescanso(boolean descanso) {
		this.descanso = descanso;
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

    
}
