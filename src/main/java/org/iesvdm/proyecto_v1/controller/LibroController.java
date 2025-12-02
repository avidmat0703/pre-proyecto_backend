package org.iesvdm.proyecto_v1.controller;

import org.iesvdm.proyecto_v1.model.Autor;
import org.iesvdm.proyecto_v1.model.Libro;
import org.iesvdm.proyecto_v1.service.LibroService;
import org.iesvdm.proyecto_v1.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorService autorService;

    // Crear un nuevo libro → Solo ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro, @RequestParam Long autorId) {
        Autor autor = autorService.obtenerAutorPorId(autorId);
        if (autor == null) return ResponseEntity.badRequest().build();

        libro.setAutor(autor);
        Libro libroGuardado = libroService.guardarLibro(libro);
        return ResponseEntity.ok(libroGuardado);
    }

    // Obtener todos los libros paginados → TODOS
    @GetMapping
    public ResponseEntity<Page<Libro>> obtenerLibros(Pageable pageable) {
        Page<Libro> libros = libroService.obtenerLibrosPaginados(pageable);
        return ResponseEntity.ok(libros);
    }

    // Buscar libros por título (paginación) → TODOS
    @GetMapping("/buscar")
    public ResponseEntity<Page<Libro>> buscarLibrosPorTitulo(@RequestParam String titulo, Pageable pageable) {
        Page<Libro> libros = libroService.buscarPorTitulo(titulo, pageable);
        return ResponseEntity.ok(libros);
    }

    // Obtener un libro por ID → TODOS
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        Libro libro = libroService.obtenerLibroPorId(id);
        if (libro == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(libro);
    }

    // Modificar libro → Solo ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Libro> modificarLibro(@PathVariable Long id,
                                                @RequestBody Libro libro,
                                                @RequestParam Long autorId) {
        Libro libroExistente = libroService.obtenerLibroPorId(id);
        if (libroExistente == null) return ResponseEntity.notFound().build();

        Autor autor = autorService.obtenerAutorPorId(autorId);
        if (autor == null) return ResponseEntity.badRequest().build();

        if (libro.getTitulo() != null) libroExistente.setTitulo(libro.getTitulo());
        if (libro.getDescripcion() != null) libroExistente.setDescripcion(libro.getDescripcion());

        libroExistente.setAutor(autor);
        Libro libroModificado = libroService.guardarLibro(libroExistente);
        return ResponseEntity.ok(libroModificado);
    }

    // Eliminar libro → Solo ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        Libro libroExistente = libroService.obtenerLibroPorId(id);
        if (libroExistente == null) return ResponseEntity.notFound().build();

        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
}