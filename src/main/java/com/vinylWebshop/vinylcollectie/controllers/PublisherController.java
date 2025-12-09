package com.vinylWebshop.vinylcollectie.controllers;

import com.vinylWebshop.vinylcollectie.entities.PublisherEntity;
import com.vinylWebshop.vinylcollectie.services.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller voor het beheren van uitgevers (PublisherEntity).
 * Maakt gebruik van RESTful endpoints.
 */
@RestController // Geeft aan dat deze klasse een REST-controller is (JSON in/uit)
@RequestMapping("/publishers") // Alle endpoints beginnen met /publishers
public class PublisherController {

    private final PublisherService publisherService;

    /**
     * Constructor-injectie van de service.
     */
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    /**
     * Haal een lijst van alle uitgevers op.
     * Endpoint: GET /publishers
     */
    @GetMapping
    public ResponseEntity<List<PublisherEntity>> getAllPublishers() {
        List<PublisherEntity> publishers = publisherService.findAllPublishers();
        return ResponseEntity.ok(publishers); // HTTP 200 OK
    }

    /**
     * Haal één uitgever op via zijn id.
     * Endpoint: GET /publishers/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<PublisherEntity> getPublisherById(@PathVariable Long id) {
        PublisherEntity publisher = publisherService.findPublisherById(id);
        if (publisher == null) {
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found als niet gevonden
        }
        return ResponseEntity.ok(publisher); // HTTP 200 OK
    }

    /**
     * Maak een nieuwe uitgever aan.
     * Endpoint: POST /publishers
     */
    @PostMapping
    public ResponseEntity<PublisherEntity> createPublisher(@RequestBody PublisherEntity input) {
        PublisherEntity saved = publisherService.createPublisher(input);
        // Je kunt hier eventueel een Location-header toevoegen met de url van de nieuwe resource
        return ResponseEntity.status(201).body(saved); // HTTP 201 Created
    }

    /**
     * Werk een bestaande uitgever bij.
     * Endpoint: PUT /publishers/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<PublisherEntity> updatePublisher(@PathVariable Long id, @RequestBody PublisherEntity input) {
        PublisherEntity updated = publisherService.updatePublisher(id, input);
        if (updated == null) {
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found als niet gevonden
        }
        return ResponseEntity.ok(updated); // HTTP 200 OK
    }

    /**
     * Verwijder een uitgever op basis van id.
     * Endpoint: DELETE /publishers/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}
