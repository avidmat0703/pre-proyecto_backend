package org.iesvdm.proyecto_v1.exception;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(Long id) {
        super("Usuario no encontrado con ID: " + id);
    }
}