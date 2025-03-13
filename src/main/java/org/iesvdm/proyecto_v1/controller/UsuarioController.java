package org.iesvdm.proyecto_v1.controller;

import org.iesvdm.proyecto_v1.model.Usuario;
import org.iesvdm.proyecto_v1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // Modificar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> modificarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioModificado = usuarioService.modificarUsuario(id, usuario);
        if (usuarioModificado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioModificado);
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        // Verificar si el usuario existe
        Usuario usuarioExistente = usuarioService.obtenerUsuarioPorId(id);

        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build(); // Si no existe el usuario, devolver error 404
        }

        // Eliminar el usuario
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build(); // Retornar código 204 (sin contenido)
    }
}