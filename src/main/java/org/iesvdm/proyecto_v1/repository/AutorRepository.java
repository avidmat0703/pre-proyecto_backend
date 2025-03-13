package org.iesvdm.proyecto_v1.repository;

import org.iesvdm.proyecto_v1.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}