package com.tareas.tareainvidual.controller;

import com.tareas.tareainvidual.models.Usuario;
import com.tareas.tareainvidual.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class UserController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUser() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/login/{email}/{contrasena}")
    public ResponseEntity<Usuario> login(@PathVariable("email") String email, @PathVariable("contrasena") String contrasena) {
        Usuario usuario = usuarioRepository.findByEmailAndContrasena(email, contrasena);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) {
        boolean checkEmail = usuarioRepository.existsByEmail(usuario.getEmail());
        if (!checkEmail) {
            Usuario newUser = usuarioRepository.save(usuario);
            return ResponseEntity.ok(newUser);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable("id") Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Usuario> deleteUser(@PathVariable("id") Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuarioRepository.delete(usuario);
            return ResponseEntity.ok(usuario);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Usuario> updateUser(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        Usuario actual = usuarioRepository.findById(id).orElse(null);
        if (actual != null) {
            usuario.setId(id);
            Usuario up = usuarioRepository.saveAndFlush(usuario);
            return ResponseEntity.ok(up);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}