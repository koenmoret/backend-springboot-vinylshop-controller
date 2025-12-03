package com.vinylWebshop.vinylcollectie.services;

import com.vinylWebshop.vinylcollectie.entities.PublisherEntity;
import com.vinylWebshop.vinylcollectie.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviceklasse voor PublisherEntity.
 * Hier zet je alle logica die te maken heeft met uitgevers beheren.
 */
@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    /**
     * Constructor-injectie van de repository.
     */
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    /**
     * Haal alle uitgevers op uit de database.
     */
    public List<PublisherEntity> findAllPublishers() {
        return publisherRepository.findAll();
    }

    /**
     * Haal één uitgever op op basis van zijn id.
     * Als de uitgever niet bestaat, returnt de methode null.
     */
    public PublisherEntity findPublisherById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    /**
     * Maak een nieuwe uitgever aan en sla deze op in de database.
     */
    public PublisherEntity createPublisher(PublisherEntity input) {
        return publisherRepository.save(input);
    }

    /**
     * Werk een bestaande uitgever bij (op basis van id).
     * Alleen naam, adres en contactDetails kunnen gewijzigd worden.
     * Als de uitgever niet bestaat, returnt deze methode null.
     */
    public PublisherEntity updatePublisher(Long id, PublisherEntity input) {
        PublisherEntity existing = getPublisherById(id);
        if (existing == null) {
            return null;
        }
        existing.setName(input.getName());
        existing.setAddress(input.getAddress());
        existing.setContactDetails(input.getContactDetails());
        return publisherRepository.save(existing);
    }

    /**
     * Verwijder een uitgever op basis van id.
     * Als de uitgever niet bestaat, gebeurt er niks.
     */
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }

    /**
     * Private helper-methode om een uitgever veilig op te halen.
     */
    private PublisherEntity getPublisherById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }
}
