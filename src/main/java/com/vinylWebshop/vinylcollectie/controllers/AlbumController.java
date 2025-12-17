package com.vinylWebshop.vinylcollectie.controllers;

import com.vinylWebshop.vinylcollectie.dtos.album.AlbumExtendedResponseDTO;
import com.vinylWebshop.vinylcollectie.dtos.album.AlbumRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.album.AlbumResponseDTO;
import com.vinylWebshop.vinylcollectie.dtos.artist.ArtistResponseDTO;
import com.vinylWebshop.vinylcollectie.helpers.UrlHelper;
import com.vinylWebshop.vinylcollectie.services.AlbumService;
import com.vinylWebshop.vinylcollectie.services.ArtistService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final ArtistService artistService;
    private final UrlHelper urlHelper;

    public AlbumController(AlbumService albumService, ArtistService artistService, UrlHelper urlHelper) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.urlHelper = urlHelper;
    }

    @PostMapping
    public ResponseEntity<AlbumResponseDTO> create(@RequestBody @Valid AlbumRequestDTO request) {
        AlbumResponseDTO created = albumService.createAlbum(request);
        return ResponseEntity
                .created(urlHelper.getCurrentUrlWithId(created.getId()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<AlbumResponseDTO>> getAll(
            @RequestParam(required = false) Boolean stock
    ) {
        if (stock != null) {
            return ResponseEntity.ok(albumService.getAlbumsWithStock(stock));
        }
        return ResponseEntity.ok(albumService.getAll());
    }

    // Stap 7: GET /albums/{id} -> AlbumExtendedResponseDTO (met stock)
    @GetMapping("/{id}")
    public ResponseEntity<AlbumExtendedResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid AlbumRequestDTO request
    ) {
        return ResponseEntity.ok(albumService.updateAlbum(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/artists")
    public ResponseEntity<List<ArtistResponseDTO>> getArtistsForAlbum(@PathVariable Long id) {
        List<ArtistResponseDTO> artists = artistService.getArtistsForAlbum(id);
        return ResponseEntity.ok(artists);
    }

}
