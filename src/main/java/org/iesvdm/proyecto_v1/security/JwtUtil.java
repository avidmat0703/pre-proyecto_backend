package org.iesvdm.proyecto_v1.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.iesvdm.proyecto_v1.model.Usuario;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "A9d82JkL0pqZsJ3871nxMZqP0LfT92mdKxB1C8rP5WfE6GsJrT3H9Q0L2V7B8N4";

    // Generar token a partir de email y rol
    public String generarToken(String email, String rol) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", rol);
        return crearToken(claims, email);
    }

    private String crearToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Obtener email del token
    public String obtenerEmail(String token) {
        return obtenerClaim(token, Claims::getSubject);
    }

    // Obtener rol del token
    public String obtenerRol(String token) {
        Claims claims = obtenerTodosLosClaims(token);
        return claims.get("rol", String.class);
    }

    public <T> T obtenerClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = obtenerTodosLosClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims obtenerTodosLosClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // Validar token
    public boolean validarToken(String token, Usuario usuario) {
        final String email = obtenerEmail(token);
        return (email.equals(usuario.getEmail()) && !estaExpirado(token));
    }

    private boolean estaExpirado(String token) {
        return obtenerClaim(token, Claims::getExpiration).before(new Date());
    }
}