package org.iesvdm.proyecto_v1.controller;

import org.iesvdm.proyecto_v1.model.Libro;
import org.iesvdm.proyecto_v1.model.Reseña;
import org.iesvdm.proyecto_v1.model.Usuario;
import org.iesvdm.proyecto_v1.service.LibroService;
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

    @Autowired
    private LibroService libroService;

    // Obtener todas las reseñas → TODOS
    @GetMapping
    public ResponseEntity<List<Reseña>> obtenerTodasLasReseñas() {
        return ResponseEntity.ok(reseñaService.obtenerTodasLasReseñas());
    }

    // Obtener una reseña por ID → TODOS
    @GetMapping("/{id}")
    public ResponseEntity<Reseña> obtenerReseñaPorId(@PathVariable Long id) {
        Reseña reseña = reseñaService.obtenerReseñaPorId(id);
        if (reseña == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(reseña);
    }

    // Crear una reseña → USER o ADMIN
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Reseña> crearReseña(@RequestBody Reseña reseña, Authentication auth) {

        // Obtener el usuario autenticado
        String email = auth.getName();
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email);
        reseña.setUsuario(usuario);

        // VALIDAR QUE EL LIBRO EXISTE
        Long libroId = reseña.getLibro().getId();
        Libro libro = libroService.obtenerLibroPorId(libroId);
        if (libro == null) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        reseña.setLibro(libro); // asigna entidad válida y gestionada por JPA

        // Guardar la reseña
        Reseña nueva = reseñaService.crearReseña(reseña);
        return ResponseEntity.ok(nueva);
    }

    // Modificar reseña → USER dueño o ADMIN
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Reseña> modificarReseña(
            @PathVariable Long id,
            @RequestBody Reseña reseña,
            Authentication auth) {

        Reseña existente = reseñaService.obtenerReseñaPorId(id);
        if (existente == null)
            return ResponseEntity.notFound().build();

        String email = auth.getName();
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email);

        // Permitido solo si es ADMIN o dueño de la reseña
        if (!usuario.getRol().equals("ADMIN")
                && !existente.getUsuario().getId().equals(usuario.getId())) {
            return ResponseEntity.status(403).build();
        }

        // Actualizar datos
        existente.setComentario(reseña.getComentario());
        existente.setCalificacion(reseña.getCalificacion());

        Reseña modificada = reseñaService.modificarReseña(id, existente);
        return ResponseEntity.ok(modificada);
    }

    // Eliminar reseña → USER dueño o ADMIN
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReseña(@PathVariable Long id, Authentication auth) {

        Reseña existente = reseñaService.obtenerReseñaPorId(id);
        if (existente == null)
            return ResponseEntity.notFound().build();

        String email = auth.getName();
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email);

        // Permitido solo si es ADMIN o dueño
        if (!usuario.getRol().equals("ADMIN")
                && !existente.getUsuario().getId().equals(usuario.getId())) {
            return ResponseEntity.status(403).build();
        }

        reseñaService.eliminarReseña(id);
        return ResponseEntity.noContent().build();
    }
}