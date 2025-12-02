package org.iesvdm.proyecto_v1.controller;

import org.iesvdm.proyecto_v1.model.Reseña;
import org.iesvdm.proyecto_v1.model.Usuario;
import org.iesvdm.proyecto_v1.service.ReseñaService;
import org.iesvdm.proyecto_v1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/resenas")
public class ReseñaController {

    @Autowired
    private ReseñaService reseñaService;

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todas las reseñas → TODOS
    @GetMapping
    public ResponseEntity<List<Reseña>> obtenerTodasLasReseñas() {
        List<Reseña> resenas = reseñaService.obtenerTodasLasReseñas();
        return ResponseEntity.ok(resenas);
    }

    // Obtener una reseña por ID → TODOS
    @GetMapping("/{id}")
    public ResponseEntity<Reseña> obtenerReseñaPorId(@PathVariable Long id) {
        Reseña reseña = reseñaService.obtenerReseñaPorId(id);
        if (reseña == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reseña);
    }

    // Crear una reseña → USUARIO o ADMIN
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Reseña> crearReseña(@RequestBody Reseña reseña, Authentication auth) {
        String email = auth.getName();
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email);
        reseña.setUsuario(usuario);
        Reseña nuevaReseña = reseñaService.crearReseña(reseña);
        return ResponseEntity.ok(nuevaReseña);
    }

    // Modificar una reseña → USUARIO (propia) o ADMIN
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Reseña> modificarReseña(@PathVariable Long id, @RequestBody Reseña reseña, Authentication auth) {
        Reseña existente = reseñaService.obtenerReseñaPorId(id);
        if (existente == null) return ResponseEntity.notFound().build();

        String email = auth.getName();
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email);

        if (!usuario.getRol().equals("ADMIN") && !existente.getUsuario().getId().equals(usuario.getId())) {
            return ResponseEntity.status(403).build(); // Forbidden si no es admin ni dueño
        }

        existente.setComentario(reseña.getComentario());
        existente.setCalificacion(reseña.getCalificacion());
        Reseña modificada = reseñaService.modificarReseña(id, existente);
        return ResponseEntity.ok(modificada);
    }

    // Eliminar una reseña → USUARIO (propia) o ADMIN
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReseña(@PathVariable Long id, Authentication auth) {
        Reseña existente = reseñaService.obtenerReseñaPorId(id);
        if (existente == null) return ResponseEntity.notFound().build();

        String email = auth.getName();
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email);

        if (!usuario.getRol().equals("ADMIN") && !existente.getUsuario().getId().equals(usuario.getId())) {
            return ResponseEntity.status(403).build(); // Forbidden si no es admin ni dueño
        }

        reseñaService.eliminarReseña(id);
        return ResponseEntity.noContent().build();
    }
}