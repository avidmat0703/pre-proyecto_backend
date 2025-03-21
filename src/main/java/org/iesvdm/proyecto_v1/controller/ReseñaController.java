package org.iesvdm.proyecto_v1.controller;

import org.iesvdm.proyecto_v1.model.Reseña;
import org.iesvdm.proyecto_v1.service.ReseñaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/api/reseñas")
public class ReseñaController {

    @Autowired
    private ReseñaService reseñaService;

    // Obtener todas las reseñas
    @GetMapping
    public ResponseEntity<List<Reseña>> obtenerTodasLasReseñas() {
        List<Reseña> reseñas = reseñaService.obtenerTodasLasReseñas();
        return ResponseEntity.ok(reseñas);
    }

    // Obtener una reseña por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reseña> obtenerReseñaPorId(@PathVariable Long id) {
        Reseña reseña = reseñaService.obtenerReseñaPorId(id);
        if (reseña == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reseña);
    }

    // Crear una reseña
    @PostMapping
    public Reseña crearReseña(@RequestBody Reseña reseña) {
        return reseñaService.crearReseña(reseña);
    }

    // Modificar una reseña
    @PutMapping("/{id}")
    public Reseña modificarReseña(@PathVariable Long id, @RequestBody Reseña reseña) {
        return reseñaService.modificarReseña(id, reseña);
    }

    // Eliminar una reseña
    @DeleteMapping("/{id}")
    public void eliminarReseña(@PathVariable Long id) {
        reseñaService.eliminarReseña(id);
    }
}