package com.vinylWebshop.vinylcollectie.controllers;

import com.vinylWebshop.vinylcollectie.entities.GenreEntity;
import com.vinylWebshop.vinylcollectie.services.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // GET /genres - Haal alle genres op
    @GetMapping
    public List<GenreEntity> getAllGenres() {
        return genreService.findAllGenres();
    }

    // GET /genres/{id} - Haal één genre op
    @GetMapping("/{id}")
    public ResponseEntity<GenreEntity> getGenreById(@PathVariable Long id) {
        try {
            GenreEntity genre = genreService.findGenreById(id);
            return ResponseEntity.ok(genre);
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /genres - Maak een nieuw genre aan
    @PostMapping
    public ResponseEntity<GenreEntity> createGenre(@RequestBody GenreEntity genreEntity) {
        GenreEntity createdGenre = genreService.createGenre(genreEntity);
        return new ResponseEntity<>(createdGenre, HttpStatus.CREATED);
    }

    // PUT /genres/{id} - Update een genre
    @PutMapping("/{id}")
    public ResponseEntity<GenreEntity> updateGenre(@PathVariable Long id, @RequestBody GenreEntity genreInput) {
        try {
            GenreEntity updatedGenre = genreService.updateGenre(id, genreInput);
            return ResponseEntity.ok(updatedGenre);
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /genres/{id} - Verwijder een genre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        try {
            genreService.deleteGenre(id);
            return ResponseEntity.noContent().build();
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
