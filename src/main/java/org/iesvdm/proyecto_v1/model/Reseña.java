package org.iesvdm.proyecto_v1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Reseña {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentario;  // Comentario de la reseña
    private int calificacion;   // Calificación del libro (por ejemplo, del 1 al 5)

    // Relación Many-to-One con Libro (una reseña pertenece a un solo libro)
    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)  // La columna 'libro_id' será la clave foránea
    @JsonBackReference("l_r")  // Evita la recursión infinita durante la serialización de JSON
    private Libro libro;

    // Relación Many-to-One con Usuario (una reseña es escrita por un solo usuario)
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)  // La columna 'usuario_id' será la clave foránea
    @JsonBackReference("u_r")  // Evita la recursión infinita durante la serialización de JSON
    private Usuario usuario;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}