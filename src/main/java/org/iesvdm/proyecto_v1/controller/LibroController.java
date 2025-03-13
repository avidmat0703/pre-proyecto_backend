package org.iesvdm.proyecto_v1.controller;

import org.iesvdm.proyecto_v1.model.Autor;
import org.iesvdm.proyecto_v1.model.Libro;
import org.iesvdm.proyecto_v1.service.LibroService;
import org.iesvdm.proyecto_v1.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorService autorService;

    // Crear un nuevo libro
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro, @RequestParam Long autorId) {
        // Buscar el autor por ID
        Autor autor = autorService.obtenerAutorPorId(autorId); // Obtener el autor usando el ID proporcionado

        if (autor == null) {
            return ResponseEntity.badRequest().build(); // Si no se encuentra el autor, devolver error 400
        }

        // Asignar el autor al libro
        libro.setAutor(autor);

        // Guardar el libro
        Libro libroGuardado = libroService.guardarLibro(libro);
        return ResponseEntity.ok(libroGuardado); // Retornar el libro guardado
    }

    // Obtener todos los libros paginados
    @GetMapping
    public ResponseEntity<Page<Libro>> obtenerLibros(Pageable pageable) {
        Page<Libro> libros = libroService.obtenerLibrosPaginados(pageable);
        return ResponseEntity.ok(libros);
    }

    // Buscar libros por título (con paginación)
    @GetMapping("/buscar")
    public ResponseEntity<Page<Libro>> buscarLibrosPorTitulo(@RequestParam String titulo, Pageable pageable) {
        Page<Libro> libros = libroService.buscarPorTitulo(titulo, pageable);
        return ResponseEntity.ok(libros);
    }

    // Obtener un libro por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        Libro libro = libroService.obtenerLibroPorId(id);

        if (libro == null) {
            return ResponseEntity.notFound().build(); // Si no existe, devuelve 404
        }

        return ResponseEntity.ok(libro); // Devuelve el libro encontrado
    }

    // Modificar libro
    @PutMapping("/{id}")
    public ResponseEntity<Libro> modificarLibro(@PathVariable Long id,
                                                @RequestBody Libro libro,
                                                @RequestParam Long autorId) {
        // Buscar el libro por ID
        Libro libroExistente = libroService.obtenerLibroPorId(id);
        if (libroExistente == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no existe el libro
        }

        // Buscar el autor por ID
        Autor autor = autorService.obtenerAutorPorId(autorId);
        if (autor == null) {
            return ResponseEntity.badRequest().build(); // Retorna 400 si el autor no existe
        }

        // Actualizar solo los campos necesarios
        if (libro.getTitulo() != null) {
            libroExistente.setTitulo(libro.getTitulo());
        }
        if (libro.getDescripcion() != null) {
            libroExistente.setDescripcion(libro.getDescripcion());
        }

        // Establecer el nuevo autor
        libroExistente.setAutor(autor);

        // Guardar el libro actualizado
        Libro libroModificado = libroService.guardarLibro(libroExistente);
        return ResponseEntity.ok(libroModificado);
    }

    // Eliminar libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        // Verificar si el libro existe
        Libro libroExistente = libroService.obtenerLibroPorId(id);

        if (libroExistente == null) {
            return ResponseEntity.notFound().build(); // Si no existe el libro, devolver error 404
        }

        // Eliminar el libro
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build(); // Retornar código 204 (sin contenido)
    }
}