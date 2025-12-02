package org.iesvdm.proyecto_v1.exception;

public class AutorNotFoundException extends RuntimeException {
    public AutorNotFoundException(Long id) {
        super("Autor no encontrado con ID: " + id);
    }
}