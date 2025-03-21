package org.iesvdm.proyecto_v1.controller;

import org.iesvdm.proyecto_v1.model.Autor;
import org.iesvdm.proyecto_v1.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/api/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    // Obtener todos los autores
    @GetMapping
    public ResponseEntity<List<Autor>> obtenerTodosLosAutores() {
        List<Autor> autores = autorService.obtenerTodosLosAutores();
        return ResponseEntity.ok(autores);
    }

    // Obtener un autor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Autor> obtenerAutorPorId(@PathVariable Long id) {
        Autor autor = autorService.obtenerAutorPorId(id);
        if (autor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autor);
    }

    // Crear un nuevo autor
    @PostMapping
    public ResponseEntity<Autor> crearAutor(@RequestBody Autor autor) {
        Autor nuevoAutor = autorService.crearAutor(autor);
        return ResponseEntity.ok(nuevoAutor);
    }

    // Modificar un autor existente
    @PutMapping("/{id}")
    public ResponseEntity<Autor> modificarAutor(@PathVariable Long id, @RequestBody Autor autor) {
        Autor autorModificado = autorService.modificarAutor(id, autor);
        if (autorModificado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autorModificado);
    }

    // Eliminar un autor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAutor(@PathVariable Long id) {
        // Verificar si el autor existe
        Autor autorExistente = autorService.obtenerAutorPorId(id);

        if (autorExistente == null) {
            return ResponseEntity.notFound().build(); // Si no existe el autor, devolver error 404
        }

        // Eliminar el autor
        autorService.eliminarAutor(id);
        return ResponseEntity.noContent().build(); // Retornar código 204 (sin contenido)
    }
}