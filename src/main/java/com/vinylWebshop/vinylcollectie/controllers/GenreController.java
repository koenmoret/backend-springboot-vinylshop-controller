package com.vinylWebshop.vinylcollectie.controllers;

import com.vinylWebshop.vinylcollectie.entities.GenreEntity;
import com.vinylWebshop.vinylcollectie.helpers.UrlHelper;
import com.vinylWebshop.vinylcollectie.services.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controller voor het beheren van muziekgenres.
 * RESTful endpoints voor GenreEntity.
 */
@RestController
@RequestMapping("/genres") // Alle genre-routes beginnen met /genres
public class GenreController {

    private final GenreService genreService;

    /**
     * Constructor-injectie van de service.
     */
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    /**
     * Haal een lijst op van alle genres.
     * GET /genres
     */
    @GetMapping
    public ResponseEntity<List<GenreEntity>> getAllGenres() {
        return ResponseEntity.ok(genreService.findAllGenres());
    }

    /**
     * Haal één genre op via id.
     * GET /genres/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<GenreEntity> getGenreById(@PathVariable Long id) {
        GenreEntity genre = genreService.findGenreById(id);
        if (genre == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found als genre niet bestaat
        }
        return ResponseEntity.ok(genre); // 200 OK
    }

    /**
     * Maak een nieuw genre aan.
     * POST /genres
     */
    @PostMapping
    public ResponseEntity<GenreEntity> createGenre(@RequestBody GenreEntity genreEntity) {
        GenreEntity createdGenre = genreService.createGenre(genreEntity);
        // Bouw Location header op met helper
        URI location = UrlHelper.buildLocationUri(createdGenre.getId(), "/genres/");
        return ResponseEntity.created(location).body(createdGenre); // 201 Created + Location-header
    }

    /**
     * Werk een bestaand genre bij.
     * PUT /genres/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<GenreEntity> updateGenre(@PathVariable Long id, @RequestBody GenreEntity genreInput) {
        GenreEntity updatedGenre = genreService.updateGenre(id, genreInput);
        if (updatedGenre == null) {
            return ResponseEntity.notFound().build(); // 404 als genre niet bestaat
        }
        return ResponseEntity.ok(updatedGenre); // 200 OK
    }

    /**
     * Verwijder een genre.
     * DELETE /genres/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        GenreEntity genre = genreService.findGenreById(id);
        if (genre == null) {
            return ResponseEntity.notFound().build(); // 404 als genre niet bestaat
        }
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build(); // 204 No Content bij succesvol verwijderen
    }
}
