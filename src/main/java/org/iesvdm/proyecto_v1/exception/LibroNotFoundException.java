package org.iesvdm.proyecto_v1.exception;

public class LibroNotFoundException extends RuntimeException {
    public LibroNotFoundException(Long id) {
        super("Libro no encontrado con ID: " + id);
    }
}