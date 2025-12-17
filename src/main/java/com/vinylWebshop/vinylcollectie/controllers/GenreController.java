package com.vinylWebshop.vinylcollectie.controllers;

import com.vinylWebshop.vinylcollectie.dtos.genre.GenreRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.genre.GenreResponseDTO;
import com.vinylWebshop.vinylcollectie.helpers.UrlHelper;
import com.vinylWebshop.vinylcollectie.services.GenreService;
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

    @PostMapping
    public ResponseEntity<GenreResponseDTO> create(
            @RequestBody @Valid GenreRequestDTO request) {

        GenreResponseDTO created = genreService.create(request);
        return ResponseEntity
                .created(urlHelper.getCurrentUrlWithId(created.getId()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<GenreResponseDTO>> getAll() {
        return ResponseEntity.ok(genreService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid GenreRequestDTO request) {

        return ResponseEntity.ok(genreService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
