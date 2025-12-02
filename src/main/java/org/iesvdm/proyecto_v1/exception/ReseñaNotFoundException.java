package org.iesvdm.proyecto_v1.exception;

public class ReseñaNotFoundException extends RuntimeException {
    public ReseñaNotFoundException(Long id) {
        super("Reseña no encontrada con ID: " + id);
    }
}