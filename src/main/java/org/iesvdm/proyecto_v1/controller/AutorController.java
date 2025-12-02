package org.iesvdm.proyecto_v1.controller;

import org.iesvdm.proyecto_v1.model.Autor;
import org.iesvdm.proyecto_v1.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    // Obtener todos los autores → TODOS
    @GetMapping
    public ResponseEntity<List<Autor>> obtenerTodosLosAutores() {
        return ResponseEntity.ok(autorService.obtenerTodosLosAutores());
    }

    // Obtener un autor por ID → TODOS
    @GetMapping("/{id}")
    public ResponseEntity<Autor> obtenerAutorPorId(@PathVariable Long id) {
        Autor autor = autorService.obtenerAutorPorId(id);
        return ResponseEntity.ok(autor);
    }

    // Crear un autor → SOLO ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Autor> crearAutor(@RequestBody Autor autor) {
        Autor nuevoAutor = autorService.crearAutor(autor);
        return ResponseEntity.ok(nuevoAutor);
    }

    // Modificar un autor → SOLO ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Autor> modificarAutor(@PathVariable Long id, @RequestBody Autor autor) {
        Autor modificado = autorService.modificarAutor(id, autor);
        return ResponseEntity.ok(modificado);
    }

    // Eliminar un autor → SOLO ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAutor(@PathVariable Long id) {
        autorService.eliminarAutor(id);
        return ResponseEntity.noContent().build();
    }
}