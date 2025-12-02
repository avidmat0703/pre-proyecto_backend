package org.iesvdm.proyecto_v1.controller;

import org.iesvdm.proyecto_v1.model.Usuario;
import org.iesvdm.proyecto_v1.service.UsuarioService;
import org.iesvdm.proyecto_v1.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthenticationController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    // Login de usuario y generación de JWT
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        // Buscar usuario por email
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email);

        // Validar contraseña
        if (!usuario.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        // Generar token JWT con email y rol
        String token = jwtUtil.generarToken(usuario.getEmail(), usuario.getRol());

        return ResponseEntity.ok(token);
    }
}