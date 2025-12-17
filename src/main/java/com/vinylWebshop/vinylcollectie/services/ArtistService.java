package com.vinylWebshop.vinylcollectie.services;

import com.vinylWebshop.vinylcollectie.dtos.artist.ArtistRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.artist.ArtistResponseDTO;
import com.vinylWebshop.vinylcollectie.entities.ArtistEntity;
import com.vinylWebshop.vinylcollectie.exceptions.ResourceNotFoundException;
import com.vinylWebshop.vinylcollectie.mappers.ArtistDTOMapper;
import com.vinylWebshop.vinylcollectie.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistDTOMapper artistMapper;

    public ArtistService(ArtistRepository artistRepository, ArtistDTOMapper artistMapper) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
    }

    public List<ArtistResponseDTO> getAll() {
        return artistRepository.findAll()
                .stream()
                .map(artistMapper::toDto)
                .toList();
    }

    public ArtistResponseDTO getById(Long id) {
        ArtistEntity entity = artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist %d niet gevonden".formatted(id)));
        return artistMapper.toDto(entity);
    }

    public ArtistResponseDTO create(ArtistRequestDTO request) {
        ArtistEntity entity = new ArtistEntity();
        artistMapper.copyToEntity(request, entity);

        ArtistEntity saved = artistRepository.save(entity);
        return artistMapper.toDto(saved);
    }

    public ArtistResponseDTO update(Long id, ArtistRequestDTO request) {
        ArtistEntity entity = artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist %d niet gevonden".formatted(id)));

        artistMapper.copyToEntity(request, entity);
        ArtistEntity saved = artistRepository.save(entity);
        return artistMapper.toDto(saved);
    }

    public void delete(Long id) {
        if (!artistRepository.existsById(id)) {
            throw new ResourceNotFoundException("Artist %d niet gevonden".formatted(id));
        }
        artistRepository.deleteById(id);
    }

    public List<ArtistResponseDTO> getArtistsForAlbum(Long albumId) {
        return artistRepository.findArtistsByAlbumsId(albumId)
                .stream()
                .map(artistMapper::toDto)
                .toList();
    }

}
