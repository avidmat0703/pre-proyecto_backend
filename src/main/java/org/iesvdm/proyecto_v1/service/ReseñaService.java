package org.iesvdm.proyecto_v1.service;

import org.iesvdm.proyecto_v1.exception.ReseñaNotFoundException;
import org.iesvdm.proyecto_v1.model.Reseña;
import org.iesvdm.proyecto_v1.repository.ReseñaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReseñaService {

    @Autowired
    private ReseñaRepository reseñaRepository;

    public List<Reseña> obtenerTodasLasReseñas() {
        return reseñaRepository.findAll();
    }

    public Reseña obtenerReseñaPorId(Long id) {
        return reseñaRepository.findById(id)
                .orElseThrow(() -> new ReseñaNotFoundException(id));
    }

    public Reseña crearReseña(Reseña reseña) {
        validarReseña(reseña);
        return reseñaRepository.save(reseña);
    }

    public Reseña modificarReseña(Long id, Reseña reseña) {
        Reseña existente = obtenerReseñaPorId(id);
        validarReseña(reseña);
        existente.setComentario(reseña.getComentario());
        existente.setCalificacion(reseña.getCalificacion());
        return reseñaRepository.save(existente);
    }

    public void eliminarReseña(Long id) {
        Reseña reseña = obtenerReseñaPorId(id);
        reseñaRepository.delete(reseña);
    }

    private void validarReseña(Reseña reseña) {
        if (reseña.getComentario() == null || reseña.getComentario().isBlank()) {
            throw new IllegalArgumentException("El comentario es obligatorio");
        }
        if (reseña.getCalificacion() < 1 || reseña.getCalificacion() > 5) {
            throw new IllegalArgumentException("La calificación debe estar entre 1 y 5");
        }
    }
}