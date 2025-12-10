package org.iesvdm.proyecto_v1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String isbn;
    private String imagenUrl;

    // Relación con el autor
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    // Relación con las reseñas
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("l_r")
    private Set<Reseña> reseñas = new HashSet<>();

    // RELACIÓN CON COLECCIONES (faltaba)
    @ManyToMany(mappedBy = "libros")
    @JsonBackReference("c_l") // evita recursión infinita
    private Set<Coleccion> colecciones = new HashSet<>();

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
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