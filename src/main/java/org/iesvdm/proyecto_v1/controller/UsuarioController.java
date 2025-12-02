package org.iesvdm.proyecto_v1.controller;

import org.iesvdm.proyecto_v1.model.Usuario;
import org.iesvdm.proyecto_v1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios → Solo ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios());
    }

    // Obtener usuario por ID → ADMIN o el propio usuario
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id, Authentication auth) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    // Crear usuario → Solo ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // Modificar usuario → ADMIN o el propio usuario
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> modificarUsuario(@PathVariable Long id, @RequestBody Usuario usuario, Authentication auth) {
        Usuario modificado = usuarioService.modificarUsuario(id, usuario);
        return ResponseEntity.ok(modificado);
    }

    // Eliminar usuario → ADMIN o el propio usuario
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id, Authentication auth) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}