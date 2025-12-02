package org.iesvdm.proyecto_v1.controller;

import org.iesvdm.proyecto_v1.model.Coleccion;
import org.iesvdm.proyecto_v1.model.Usuario;
import org.iesvdm.proyecto_v1.service.ColeccionService;
import org.iesvdm.proyecto_v1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/api/colecciones")
public class ColeccionController {

    @Autowired
    private ColeccionService coleccionService;

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todas las colecciones → TODOS
    @GetMapping
    public ResponseEntity<List<Coleccion>> obtenerTodasLasColecciones() {
        List<Coleccion> colecciones = coleccionService.obtenerTodasLasColecciones();
        return ResponseEntity.ok(colecciones);
    }

    // Obtener una colección por ID → TODOS
    @GetMapping("/{id}")
    public ResponseEntity<Coleccion> obtenerColeccionPorId(@PathVariable Long id) {
        Coleccion coleccion = coleccionService.obtenerColeccionPorId(id);
        if (coleccion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(coleccion);
    }

    // Crear colección → USUARIOS autenticados
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Coleccion> crearColeccion(@RequestBody Coleccion coleccion, Authentication authentication) {
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(authentication.getName());
        coleccion.setUsuario(usuario);
        return ResponseEntity.ok(coleccionService.crearColeccion(coleccion));
    }

    // Modificar colección → solo el propietario
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<Coleccion> modificarColeccion(@PathVariable Long id,
                                                        @RequestBody Coleccion coleccion,
                                                        Authentication authentication) {
        Coleccion coleccionExistente = coleccionService.obtenerColeccionPorId(id);
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(authentication.getName());

        if (coleccionExistente == null) return ResponseEntity.notFound().build();

        // Solo el dueño puede modificar
        if (!coleccionExistente.getUsuario().getId().equals(usuario.getId())) {
            return ResponseEntity.status(403).build();
        }

        coleccionExistente.setNombre(coleccion.getNombre());
        return ResponseEntity.ok(coleccionService.modificarColeccion(id, coleccionExistente));
    }

    // Eliminar colección → solo el propietario
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarColeccion(@PathVariable Long id, Authentication authentication) {
        Coleccion coleccionExistente = coleccionService.obtenerColeccionPorId(id);
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(authentication.getName());

        if (coleccionExistente == null) return ResponseEntity.notFound().build();

        // Solo el dueño puede eliminar
        if (!coleccionExistente.getUsuario().getId().equals(usuario.getId())) {
            return ResponseEntity.status(403).build();
        }

        coleccionService.eliminarColeccion(id);
        return ResponseEntity.noContent().build();
    }
}