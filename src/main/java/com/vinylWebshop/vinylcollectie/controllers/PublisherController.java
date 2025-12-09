package com.vinylWebshop.vinylcollectie.controllers;

import com.vinylWebshop.vinylcollectie.dtos.publisher.PublisherRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.publisher.PublisherResponseDTO;
import com.vinylWebshop.vinylcollectie.services.PublisherService;
import com.vinylWebshop.vinylcollectie.helpers.UrlHelper;

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

    /**
     * CREATE
     */
    @PostMapping
    public ResponseEntity<PublisherResponseDTO> createPublisher(
            @RequestBody @Valid PublisherRequestDTO dto) {

        PublisherResponseDTO newPublisher = publisherService.createPublisher(dto);

        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newPublisher.getId()))
                .body(newPublisher);
    }

    /**
     * READ ONE
     */
    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> getPublisher(@PathVariable Long id) {
        return ResponseEntity.ok(publisherService.getPublisher(id));
    }

    /**
     * READ ALL
     */
    @GetMapping
    public ResponseEntity<List<PublisherResponseDTO>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAllPublishers());
    }

    /**
     * UPDATE
     */
    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> updatePublisher(
            @PathVariable Long id,
            @RequestBody @Valid PublisherRequestDTO dto) {

        PublisherResponseDTO updated = publisherService.updatePublisher(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}
