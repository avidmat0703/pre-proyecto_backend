package org.iesvdm.proyecto_v1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String biografia;

    // Relación One-to-Many con Libro (un autor puede tener muchos libros)
    // En la entidad Libro, la relación está mapeada con @ManyToOne y @JoinColumn(name = "autor_id")
    @OneToMany(mappedBy = "autor")  // mappedBy indica que la relación es gestionada desde la clase Libro
    @JsonBackReference("l_a")  // Evita la recursión infinita al serializar a JSON
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

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }
}