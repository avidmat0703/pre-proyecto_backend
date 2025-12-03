package org.iesvdm.proyecto_v1.service;

import org.iesvdm.proyecto_v1.exception.ColeccionNotFoundException;
import org.iesvdm.proyecto_v1.model.Coleccion;
import org.iesvdm.proyecto_v1.repository.ColeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ColeccionService {

    @Autowired
    private ColeccionRepository coleccionRepository;

    public Coleccion crearColeccion(Coleccion coleccion) {
        validarColeccion(coleccion);
        return coleccionRepository.save(coleccion);
    }

    public Coleccion obtenerColeccionPorId(Long id) {
        return coleccionRepository.findById(id)
                .orElseThrow(() -> new ColeccionNotFoundException(id));
    }

    public List<Coleccion> obtenerTodasLasColecciones() {
        return coleccionRepository.findAll();
    }

    public Coleccion modificarColeccion(Long id, Coleccion coleccionActualizado) {
        Coleccion coleccion = obtenerColeccionPorId(id);
        validarColeccion(coleccionActualizado);
        coleccion.setNombre(coleccionActualizado.getNombre());
        return coleccionRepository.save(coleccion);
    }

    public void eliminarColeccion(Long id) {
        Coleccion coleccion = obtenerColeccionPorId(id);
        coleccionRepository.delete(coleccion);
    }

    private void validarColeccion(Coleccion coleccion) {
        if (coleccion.getNombre() == null || coleccion.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre de la colección es obligatorio");
        }
        if (coleccion.getUsuario() == null) {
            throw new IllegalArgumentException("La colección debe pertenecer a un usuario");
        }
    }
}