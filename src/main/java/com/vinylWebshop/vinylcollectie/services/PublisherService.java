package com.vinylWebshop.vinylcollectie.services;

import com.vinylWebshop.vinylcollectie.dtos.publisher.PublisherRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.publisher.PublisherResponseDTO;
import com.vinylWebshop.vinylcollectie.entities.AlbumEntity;
import com.vinylWebshop.vinylcollectie.entities.PublisherEntity;
import com.vinylWebshop.vinylcollectie.exceptions.ResourceNotFoundException;
import com.vinylWebshop.vinylcollectie.mappers.PublisherDTOMapper;
import com.vinylWebshop.vinylcollectie.repositories.AlbumRepository;
import com.vinylWebshop.vinylcollectie.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final AlbumRepository albumRepository;
    private final PublisherDTOMapper publisherMapper;

    public PublisherService(PublisherRepository publisherRepository, AlbumRepository albumRepository, PublisherDTOMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.albumRepository = albumRepository;
        this.publisherMapper = publisherMapper;
    }

    public List<PublisherResponseDTO> getAll() {
        return publisherRepository.findAll()
                .stream()
                .map(publisherMapper::toDto)
                .toList();
    }

    public PublisherResponseDTO getById(Long id) {
        PublisherEntity entity = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher %d niet gevonden".formatted(id)));
        return publisherMapper.toDto(entity);
    }

    public PublisherResponseDTO create(PublisherRequestDTO request) {
        PublisherEntity entity = new PublisherEntity();
        publisherMapper.copyToEntity(request, entity);

        PublisherEntity saved = publisherRepository.save(entity);
        return publisherMapper.toDto(saved);
    }

    public PublisherResponseDTO update(Long id, PublisherRequestDTO request) {
        PublisherEntity entity = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher %d niet gevonden".formatted(id)));

        publisherMapper.copyToEntity(request, entity);
        PublisherEntity saved = publisherRepository.save(entity);
        return publisherMapper.toDto(saved);
    }

    public void delete(Long id) {
        PublisherEntity publisher = publisherRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Publisher %d niet gevonden".formatted(id)));

        // Relatie verbreken
        for (AlbumEntity album : publisher.getAlbums()) {
            album.setPublisher(null);
            albumRepository.save(album);
        }

        publisherRepository.delete(publisher);
    }

}
