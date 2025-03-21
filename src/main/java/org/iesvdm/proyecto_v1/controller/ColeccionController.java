package org.iesvdm.proyecto_v1.controller;

import org.iesvdm.proyecto_v1.model.Coleccion;
import org.iesvdm.proyecto_v1.service.ColeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/api/colecciones")
public class ColeccionController {

    @Autowired
    private ColeccionService coleccionService;

    // Obtener todas las colecciones
    @GetMapping
    public ResponseEntity<List<Coleccion>> obtenerTodasLasColecciones() {
        List<Coleccion> colecciones = coleccionService.obtenerTodasLasColecciones();
        return ResponseEntity.ok(colecciones);
    }

    // Obtener una coleccion por ID
    @GetMapping("/{id}")
    public ResponseEntity<Coleccion> obtenerColeccionPorId(@PathVariable Long id) {
        Coleccion coleccion = coleccionService.obtenerColeccionPorId(id);
        if (coleccion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(coleccion);
    }

    // Crear una nueva colección
    @PostMapping
    public Coleccion crearColeccion(@RequestBody Coleccion coleccion) {
        return coleccionService.crearColeccion(coleccion);
    }

    // Modificar una colección existente
    @PutMapping("/{id}")
    public ResponseEntity<Coleccion> modificarColeccion(@PathVariable Long id, @RequestBody Coleccion coleccion) {
        Coleccion coleccionModificado = coleccionService.modificarColeccion(id, coleccion);
        if (coleccionModificado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(coleccionModificado);
    }

    // Eliminar una colección
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarColeccion(@PathVariable Long id) {
        coleccionService.eliminarColeccion(id);
        return ResponseEntity.ok().build();
    }
}