package org.iesvdm.proyecto_v1.service;

import org.iesvdm.proyecto_v1.model.Usuario;
import org.iesvdm.proyecto_v1.repository.UsuarioRepository;
import org.iesvdm.proyecto_v1.security.AuthenticationRequest;
import org.iesvdm.proyecto_v1.security.AuthenticationResponse;
import org.iesvdm.proyecto_v1.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // ---------------------------------------------------------
    // LOGIN
    // ---------------------------------------------------------
    public ResponseEntity<?> login(AuthenticationRequest request) {

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Credenciales incorrectas"));

        // Validar contraseña con BCrypt
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }

        // Generar token
        String token = jwtUtil.generarToken(usuario.getEmail(), usuario.getRol());

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }


    // ---------------------------------------------------------
    // REGISTER
    // ---------------------------------------------------------
    public ResponseEntity<?> register(Usuario usuario) {

        // Evitar duplicados
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe un usuario con ese email");
        }

        // Encriptar contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Asignar rol por defecto si no viene
        if (usuario.getRol() == null) {
            usuario.setRol("USER");
        }

        usuarioRepository.save(usuario);

        // Generar token automático al registrarse
        String token = jwtUtil.generarToken(usuario.getEmail(), usuario.getRol());

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }


    // ---------------------------------------------------------
    // CRUD NORMAL
    // ---------------------------------------------------------

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario crearUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario modificarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuario = obtenerUsuarioPorId(id);
        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setEmail(usuarioActualizado.getEmail());

        // Solo encriptar si cambió la contraseña
        if (!usuarioActualizado.getPassword().equals(usuario.getPassword())) {
            usuario.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
        }

        usuario.setRol(usuarioActualizado.getRol());
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }


    // ---------------------------------------------------------
    // JWT SUPPORT
    // ---------------------------------------------------------
    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));
    }

    @Override
    public Usuario loadUserByUsername(String email) throws UsernameNotFoundException {
        return obtenerUsuarioPorEmail(email);
    }
}