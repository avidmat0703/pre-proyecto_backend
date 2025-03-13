package org.iesvdm.proyecto_v1.service;

import org.iesvdm.proyecto_v1.model.Autor;
import org.iesvdm.proyecto_v1.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    // Obtener todos los autores
    public List<Autor> obtenerTodosLosAutores() {
        return autorRepository.findAll();
    }

    // Obtener un autor por ID
    public Autor obtenerAutorPorId(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
    }

    // Crear un autor
    public Autor crearAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    // Modificar un autor
    public Autor modificarAutor(Long id, Autor autorActualizado) {
        Autor autor = obtenerAutorPorId(id);
        autor.setNombre(autorActualizado.getNombre());
        autor.setBiografia(autorActualizado.getBiografia());
        return autorRepository.save(autor);
    }

    // Eliminar un autor
    public void eliminarAutor(Long id) {
        autorRepository.deleteById(id);
    }
}