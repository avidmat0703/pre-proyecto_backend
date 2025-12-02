package org.iesvdm.proyecto_v1.service;

import org.iesvdm.proyecto_v1.exception.LibroNotFoundException;
import org.iesvdm.proyecto_v1.model.Libro;
import org.iesvdm.proyecto_v1.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public Libro guardarLibro(Libro libro) {
        validarLibro(libro);
        return libroRepository.save(libro);
    }

    public Page<Libro> obtenerLibrosPaginados(Pageable pageable) {
        return libroRepository.findAll(pageable);
    }

    public Page<Libro> buscarPorTitulo(String titulo, Pageable pageable) {
        return libroRepository.findByTituloContaining(titulo, pageable);
    }

    public Libro obtenerLibroPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new LibroNotFoundException(id));
    }

    public void eliminarLibro(Long id) {
        Libro libro = obtenerLibroPorId(id);
        libroRepository.delete(libro);
    }

    private void validarLibro(Libro libro) {
        if (libro.getTitulo() == null || libro.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El título del libro es obligatorio");
        }
        if (libro.getAutor() == null) {
            throw new IllegalArgumentException("El libro debe tener un autor asignado");
        }
    }
}