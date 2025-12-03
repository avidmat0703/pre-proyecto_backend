package org.iesvdm.proyecto_v1.security;

import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Inyectamos el JwtFilter sin crear ciclo
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v1/api/usuarios/login").permitAll()
                        .requestMatchers("/v1/api/usuarios/register").permitAll()
                        .requestMatchers("/v1/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/api/autores/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/v1/api/autores/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/v1/api/autores/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/v1/api/autores/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/v1/api/libros/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/v1/api/libros/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/v1/api/libros/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/v1/api/libros/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/v1/api/reseñas/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/v1/api/colecciones/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // Agregamos el filtro JWT
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}