package com.vinylWebshop.vinylcollectie.controllers;

import com.vinylWebshop.vinylcollectie.entities.GenreEntity;
import com.vinylWebshop.vinylcollectie.helpers.UrlHelper;
import com.vinylWebshop.vinylcollectie.services.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // GET /genres
    @GetMapping
    public ResponseEntity<List<GenreEntity>> getAllGenres() {
        return ResponseEntity.ok(genreService.findAllGenres());
    }

    // GET /genres/{id}
    @GetMapping("/{id}")
    public ResponseEntity<GenreEntity> getGenreById(@PathVariable Long id) {
        try {
            GenreEntity genre = genreService.findGenreById(id);
            return ResponseEntity.ok(genre);
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // POST /genres
    @PostMapping
    public ResponseEntity<GenreEntity> createGenre(@RequestBody GenreEntity genreEntity) {
        GenreEntity createdGenre = genreService.createGenre(genreEntity);

        // Bouw Location header op
        URI location = UrlHelper.buildLocationUri(createdGenre.getId(), "/genres/");
        return ResponseEntity
                .created(location)
                .body(createdGenre); // 201 Created, met Location header en het gemaakte object als body
    }

    // PUT /genres/{id}
    @PutMapping("/{id}")
    public ResponseEntity<GenreEntity> updateGenre(@PathVariable Long id, @RequestBody GenreEntity genreInput) {
        try {
            GenreEntity updatedGenre = genreService.updateGenre(id, genreInput);
            return ResponseEntity.ok(updatedGenre); // 200 OK
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // DELETE /genres/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        try {
            genreService.deleteGenre(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
