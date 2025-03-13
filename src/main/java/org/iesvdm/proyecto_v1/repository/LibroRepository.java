package org.iesvdm.proyecto_v1.repository;

import org.iesvdm.proyecto_v1.model.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Metodo para buscar libros paginados
    Page<Libro> findAll(Pageable pageable);

    // Metodo para buscar libros por título (con paginación)
    Page<Libro> findByTituloContaining(String titulo, Pageable pageable);
}