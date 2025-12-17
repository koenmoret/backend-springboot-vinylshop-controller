package com.vinylWebshop.vinylcollectie.controllers;

import com.vinylWebshop.vinylcollectie.dtos.publisher.PublisherRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.publisher.PublisherResponseDTO;
import com.vinylWebshop.vinylcollectie.helpers.UrlHelper;
import com.vinylWebshop.vinylcollectie.services.PublisherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;
    private final UrlHelper urlHelper;

    public PublisherController(PublisherService publisherService, UrlHelper urlHelper) {
        this.publisherService = publisherService;
        this.urlHelper = urlHelper;
    }

    @PostMapping
    public ResponseEntity<PublisherResponseDTO> create(
            @RequestBody @Valid PublisherRequestDTO request) {

        PublisherResponseDTO created = publisherService.create(request);
        return ResponseEntity
                .created(urlHelper.getCurrentUrlWithId(created.getId()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<PublisherResponseDTO>> getAll() {
        return ResponseEntity.ok(publisherService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(publisherService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid PublisherRequestDTO request) {

        return ResponseEntity.ok(publisherService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        publisherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
