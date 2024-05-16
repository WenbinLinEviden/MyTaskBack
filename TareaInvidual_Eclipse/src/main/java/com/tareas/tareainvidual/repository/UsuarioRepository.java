package com.tareas.tareainvidual.repository;

import com.tareas.tareainvidual.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    boolean existsByEmail(String email);

    Usuario findByEmailAndContrasena(String email, String contrasena);
}
