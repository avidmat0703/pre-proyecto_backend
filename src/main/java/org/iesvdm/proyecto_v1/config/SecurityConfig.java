package org.iesvdm.proyecto_v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilita CSRF (no es necesario para APIs REST)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v1/api/libros/**").hasAnyRole("ADMIN", "USER") // Acceso a usuarios con rol ADMIN o USER
                        .requestMatchers("/v1/api/colecciones/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/v1/api/usuarios/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/v1/api/reseñas/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/v1/api/autores/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/v1/api/libros/**/modificar").hasRole("ADMIN")
                        .requestMatchers("/v1/api/libros/**/eliminar").hasRole("ADMIN")
                        .anyRequest().authenticated()  // Requiere autenticación para todas las demás peticiones
                )
                .httpBasic(withDefaults());  // Aquí sigue la autenticación básica

        return http.build();
    }

     // Crear usuarios en el Security si no tenemos implementado usuarios en la base de datos
  @Bean
    public UserDetailsService userDetailsService() {
        // Crear dos usuarios en memoria (uno admin y uno user)
        return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin123")
                        .roles("ADMIN")
                        .build(),
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user123")
                        .roles("USER")
                        .build()
        );
    }
}