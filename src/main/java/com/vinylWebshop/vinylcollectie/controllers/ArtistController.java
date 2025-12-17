package com.vinylWebshop.vinylcollectie.controllers;

import com.vinylWebshop.vinylcollectie.dtos.artist.ArtistRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.artist.ArtistResponseDTO;
import com.vinylWebshop.vinylcollectie.helpers.UrlHelper;
import com.vinylWebshop.vinylcollectie.services.ArtistService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;
    private final UrlHelper urlHelper;

    public ArtistController(ArtistService artistService, UrlHelper urlHelper) {
        this.artistService = artistService;
        this.urlHelper = urlHelper;
    }

    @PostMapping
    public ResponseEntity<ArtistResponseDTO> create(
            @RequestBody @Valid ArtistRequestDTO request) {

        ArtistResponseDTO created = artistService.create(request);
        return ResponseEntity
                .created(urlHelper.getCurrentUrlWithId(created.getId()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<ArtistResponseDTO>> getAll() {
        return ResponseEntity.ok(artistService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid ArtistRequestDTO request) {

        return ResponseEntity.ok(artistService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        artistService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
