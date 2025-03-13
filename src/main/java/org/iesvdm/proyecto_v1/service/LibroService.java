package org.iesvdm.proyecto_v1.service;

import org.iesvdm.proyecto_v1.model.Libro;
import org.iesvdm.proyecto_v1.repository.LibroRepository;
import org.iesvdm.proyecto_v1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Guardar un libro
    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // Obtener todos los libros paginados
    public Page<Libro> obtenerLibrosPaginados(Pageable pageable) {
        return libroRepository.findAll(pageable);
    }

    // Buscar libros por título (con paginación)
    public Page<Libro> buscarPorTitulo(String titulo, Pageable pageable) {
        return libroRepository.findByTituloContaining(titulo, pageable);
    }

    // Metodo para obtener un libro por ID
    public Libro obtenerLibroPorId(Long id) {
        return libroRepository.findById(id).orElse(null); // Devuelve null si no se encuentra el libro
    }

    // Metodo para eliminar el libro por ID
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }
}