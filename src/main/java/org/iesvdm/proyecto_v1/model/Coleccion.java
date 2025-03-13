package org.iesvdm.proyecto_v1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Coleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // Relación Many-to-One con Usuario (una colección pertenece a un único usuario)
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)  // La columna 'usuario_id' en la tabla 'coleccion'
    @JsonBackReference  // Evita la recursión infinita al serializar el objeto
    private Usuario usuario;

    // Relación Many-to-Many con Libro (una colección puede tener varios libros y un libro puede pertenecer a varias colecciones)
    @ManyToMany
    @JoinTable(
            name = "coleccion_libro",  // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "coleccion_id"),  // Columna de la tabla 'coleccion' que hace referencia a la relación
            inverseJoinColumns = @JoinColumn(name = "libro_id")  // Columna de la tabla 'libro' que hace referencia a la relación
    )
    private Set<Libro> libros = new HashSet<>();

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }
}