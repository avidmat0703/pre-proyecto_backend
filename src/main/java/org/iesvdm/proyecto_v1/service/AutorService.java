package org.iesvdm.proyecto_v1.service;

import org.iesvdm.proyecto_v1.exception.AutorNotFoundException;
import org.iesvdm.proyecto_v1.model.Autor;
import org.iesvdm.proyecto_v1.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> obtenerTodosLosAutores() {
        return autorRepository.findAll();
    }

    public Autor obtenerAutorPorId(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new AutorNotFoundException(id));
    }

    public Autor crearAutor(Autor autor) {
        validarAutor(autor);
        return autorRepository.save(autor);
    }

    public Autor modificarAutor(Long id, Autor autorActualizado) {
        Autor autor = obtenerAutorPorId(id);
        validarAutor(autorActualizado);
        autor.setNombre(autorActualizado.getNombre());
        autor.setBiografia(autorActualizado.getBiografia());
        return autorRepository.save(autor);
    }

    public void eliminarAutor(Long id) {
        Autor autor = obtenerAutorPorId(id);
        autorRepository.delete(autor);
    }

    private void validarAutor(Autor autor) {
        if (autor.getNombre() == null || autor.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del autor es obligatorio");
        }
    }
}