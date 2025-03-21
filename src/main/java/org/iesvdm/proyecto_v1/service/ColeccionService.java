package org.iesvdm.proyecto_v1.service;

import org.iesvdm.proyecto_v1.model.Coleccion;
import org.iesvdm.proyecto_v1.repository.ColeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ColeccionService {

    @Autowired
    private ColeccionRepository coleccionRepository;

    // Crear una nueva colección
    public Coleccion crearColeccion(Coleccion coleccion) {
        return coleccionRepository.save(coleccion);
    }

    // Obtener coleccion por id
    public Coleccion obtenerColeccionPorId(Long id) {
        return coleccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coleccion no encontrada"));
    }

    // Obtener todas las colecciones
    public List<Coleccion> obtenerTodasLasColecciones() {
        return coleccionRepository.findAll();
    }

    // Modificar una colección existente
    public Coleccion modificarColeccion(Long id, Coleccion coleccionActualizado) {
        Coleccion coleccion = obtenerColeccionPorId(id);
        coleccion.setNombre(coleccionActualizado.getNombre());
        return coleccionRepository.save(coleccion);
    }

    // Eliminar una colección
    public void eliminarColeccion(Long id) {
        Coleccion coleccion = coleccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colección no encontrada"));
        coleccionRepository.delete(coleccion);
    }
}