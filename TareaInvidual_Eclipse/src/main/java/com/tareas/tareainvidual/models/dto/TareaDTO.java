package com.tareas.tareainvidual.models.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TareaDTO implements Serializable {

        private static final long serialVersionUID = 1L;
        private Long id;
        private String init;
        private String ended;
        private String descripcion;
        private boolean descanso;
        private long calendario;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getInit() {
			return init;
		}
		public void setInit(String init) {
			this.init = init;
		}
		public String getEnded() {
			return ended;
		}
		public void setEnded(String ended) {
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
		public long getCalendario() {
			return calendario;
		}
		public void setCalendario(long calendario) {
			this.calendario = calendario;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
        
        
}
