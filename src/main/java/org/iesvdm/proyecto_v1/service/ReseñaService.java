package org.iesvdm.proyecto_v1.service;

import org.iesvdm.proyecto_v1.model.Reseña;
import org.iesvdm.proyecto_v1.repository.ReseñaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReseñaService {

    @Autowired
    private ReseñaRepository reseñaRepository;

    // Obtener todas las reseña
    public List<Reseña> obtenerTodasLasReseñas() {
        return reseñaRepository.findAll();
    }

    // Obtener una reseña por ID
    public Reseña obtenerReseñaPorId(Long id) {
        return reseñaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
    }

    // Crear una reseña
    public Reseña crearReseña(Reseña reseña) {
        return reseñaRepository.save(reseña);
    }

    // Modificar una reseña
    public Reseña modificarReseña(Long id, Reseña reseña) {
        Reseña reseñaExistente = reseñaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
        reseñaExistente.setComentario(reseña.getComentario());
        reseñaExistente.setCalificacion(reseña.getCalificacion());
        return reseñaRepository.save(reseñaExistente);
    }

    // Eliminar una reseña
    public void eliminarReseña(Long id) {
        Reseña reseña = reseñaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
        reseñaRepository.delete(reseña);
    }
}