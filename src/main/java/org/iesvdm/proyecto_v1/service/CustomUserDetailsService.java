/* package org.iesvdm.proyecto_v1.service;

import org.iesvdm.proyecto_v1.model.Usuario;
import org.iesvdm.proyecto_v1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el usuario en la base de datos por su nombre de usuario (email, en este caso)
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Retornar un UserDetails con los roles del usuario
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword()) // El password en la base de datos debe estar en un formato adecuado (usualmente encriptado)
                .roles(usuario.getRol()) // Aquí debes mapear el rol correctamente
                .build();
    }
} */