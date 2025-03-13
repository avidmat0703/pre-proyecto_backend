package org.iesvdm.proyecto_v1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    // Relación con el autor: un libro tiene un solo autor, pero un autor puede tener muchos libros
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false) // La columna 'autor_id' en la tabla 'Libro' referencia a la tabla 'Autor'
    private Autor autor; // Relación con la clase Autor

    // Relación con las reseñas: un libro puede tener muchas reseñas
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("l_r") // Para evitar la recursión infinita en la serialización JSON
    private Set<Reseña> reseñas = new HashSet<>(); // Conjunto de reseñas asociadas al libro

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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor; // Establece el autor para este libro
    }

    public Set<Reseña> getReseñas() {
        return reseñas;
    }

    public void setReseñas(Set<Reseña> reseñas) {
        this.reseñas = reseñas;
    }
}