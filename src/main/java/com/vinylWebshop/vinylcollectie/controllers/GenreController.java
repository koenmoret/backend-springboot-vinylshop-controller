package com.vinylWebshop.vinylcollectie.controllers;

import com.vinylWebshop.vinylcollectie.dtos.genre.GenreRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.genre.GenreResponseDTO;
import com.vinylWebshop.vinylcollectie.services.GenreService;
import com.vinylWebshop.vinylcollectie.helpers.UrlHelper;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;
    private final UrlHelper urlHelper;

    public GenreController(GenreService genreService, UrlHelper urlHelper) {
        this.genreService = genreService;
        this.urlHelper = urlHelper;
    }

    /**
     * CREATE (POST)
     * Ontvangt een GenreRequestDTO → valideert → maakt nieuw genre → returned ResponseDTO
     */
    @PostMapping
    public ResponseEntity<GenreResponseDTO> createGenre(@RequestBody @Valid GenreRequestDTO genreModel) {
        GenreResponseDTO newGenre = genreService.createGenre(genreModel);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newGenre.getId()))
                .body(newGenre);
    }

    /**
     * READ (GET by id)
     */
    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> getGenre(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.getGenre(id));
    }

    /**
     * READ ALL (GET all)
     */
    @GetMapping
    public ResponseEntity<List<GenreResponseDTO>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    /**
     * UPDATE (PUT)
     */
    @PutMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> updateGenre(
            @PathVariable Long id,
            @RequestBody @Valid GenreRequestDTO genreModel) {

        GenreResponseDTO updated = genreService.updateGenre(id, genreModel);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
