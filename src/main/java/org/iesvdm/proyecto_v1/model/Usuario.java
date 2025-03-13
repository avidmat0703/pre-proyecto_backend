package org.iesvdm.proyecto_v1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String password;
    private String rol; // "USER" o "ADMIN"

    // Relación One-to-Many con Reseña (un usuario puede tener muchas reseñas)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("u_r")  // Evita la recursión infinita durante la serialización de JSON
    private Set<Reseña> reseñas = new HashSet<>();

    // Relación Many-to-One con Coleccion (un usuario puede tener varias colecciones)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Coleccion> colecciones = new HashSet<>();

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Set<Reseña> getReseñas() {
        return reseñas;
    }

    public void setReseñas(Set<Reseña> reseñas) {
        this.reseñas = reseñas;
    }

    public Set<Coleccion> getColecciones() {
        return colecciones;
    }

    public void setColecciones(Set<Coleccion> colecciones) {
        this.colecciones = colecciones;
    }
}