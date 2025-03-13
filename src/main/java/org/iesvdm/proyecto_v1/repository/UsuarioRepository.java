package org.iesvdm.proyecto_v1.repository;

import org.iesvdm.proyecto_v1.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}