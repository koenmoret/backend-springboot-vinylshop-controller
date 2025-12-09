package com.vinylWebshop.vinylcollectie.services;

import com.vinylWebshop.vinylcollectie.dtos.publisher.PublisherRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.publisher.PublisherResponseDTO;
import com.vinylWebshop.vinylcollectie.entities.PublisherEntity;
import com.vinylWebshop.vinylcollectie.mappers.PublisherDTOMapper;
import com.vinylWebshop.vinylcollectie.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherDTOMapper publisherDTOMapper;

    public PublisherService(PublisherRepository publisherRepository, PublisherDTOMapper publisherDTOMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherDTOMapper = publisherDTOMapper;
    }

    /**
     * Maak nieuwe publisher aan
     */
    public PublisherResponseDTO createPublisher(PublisherRequestDTO dto) {
        PublisherEntity entity = publisherDTOMapper.mapToEntity(dto);
        entity = publisherRepository.save(entity);
        return publisherDTOMapper.mapToDto(entity);
    }

    /**
     * Ophalen van één publisher
     */
    public PublisherResponseDTO getPublisher(Long id) {
        PublisherEntity entity = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found: " + id));
        return publisherDTOMapper.mapToDto(entity);
    }

    /**
     * Ophalen van alle publishers
     */
    public List<PublisherResponseDTO> getAllPublishers() {
        List<PublisherEntity> entities = publisherRepository.findAll();
        return publisherDTOMapper.mapToDto(entities);
    }

    /**
     * Updaten van een publisher
     */
    public PublisherResponseDTO updatePublisher(Long id, PublisherRequestDTO dto) {
        PublisherEntity entity = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found: " + id));

        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setContactDetails(dto.getContactDetails());

        PublisherEntity updated = publisherRepository.save(entity);

        return publisherDTOMapper.mapToDto(updated);
    }

    /**
     * Verwijderen van een publisher
     */
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}
