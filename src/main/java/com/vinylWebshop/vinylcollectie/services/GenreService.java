package com.vinylWebshop.vinylcollectie.services;

import com.vinylWebshop.vinylcollectie.dtos.genre.GenreRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.genre.GenreResponseDTO;
import com.vinylWebshop.vinylcollectie.entities.GenreEntity;
import com.vinylWebshop.vinylcollectie.exceptions.GenreNotFoundException;
import com.vinylWebshop.vinylcollectie.mappers.GenreDTOMapper;
import com.vinylWebshop.vinylcollectie.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreDTOMapper genreDTOMapper;

    /**
     * Injecteer de repository én de mapper.
     * Spring zorgt dat beide beans beschikbaar zijn.
     */
    public GenreService(GenreRepository genreRepository, GenreDTOMapper genreDTOMapper) {
        this.genreRepository = genreRepository;
        this.genreDTOMapper = genreDTOMapper;
    }

    /**
     * Maak een nieuw genre aan.
     *
     * RequestDTO → Entity → Save → ResponseDTO
     */
    public GenreResponseDTO createGenre(GenreRequestDTO genreDTO) {
        GenreEntity genreEntity = genreDTOMapper.mapToEntity(genreDTO);
        genreEntity = genreRepository.save(genreEntity);
        return genreDTOMapper.mapToDto(genreEntity);
    }

    /**
     * Haal één genre op
     */
    public GenreResponseDTO getGenre(Long id) {
        GenreEntity entity = genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));


        return genreDTOMapper.mapToDto(entity);
    }

    /**
     * Haal ALLE genres op
     * Entity → DTO lijst
     */
    public List<GenreResponseDTO> getAllGenres() {
        List<GenreEntity> entities = genreRepository.findAll();
        return genreDTOMapper.mapToDto(entities);
    }

    /**
     * Update een genre
     */
    public GenreResponseDTO updateGenre(Long id, GenreRequestDTO dto) {
        GenreEntity entity = genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));


        // Update alleen de velden uit het DTO
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        GenreEntity updated = genreRepository.save(entity);

        return genreDTOMapper.mapToDto(updated);
    }

    /**
     * Verwijderen → return niets
     */
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
