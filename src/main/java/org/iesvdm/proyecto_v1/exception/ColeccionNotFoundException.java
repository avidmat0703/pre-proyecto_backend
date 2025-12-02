package org.iesvdm.proyecto_v1.exception;

public class ColeccionNotFoundException extends RuntimeException {
    public ColeccionNotFoundException(Long id) {
        super("Colección no encontrada con ID: " + id);
    }
}