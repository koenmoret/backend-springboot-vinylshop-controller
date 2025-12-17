package com.vinylWebshop.vinylcollectie.mappers;

import com.vinylWebshop.vinylcollectie.dtos.album.AlbumRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.album.AlbumResponseDTO;
import com.vinylWebshop.vinylcollectie.entities.AlbumEntity;
import org.springframework.stereotype.Component;

@Component
public class AlbumDTOMapper {

    private final GenreDTOMapper genreMapper;
    private final PublisherDTOMapper publisherMapper;

    public AlbumDTOMapper(GenreDTOMapper genreMapper,
                          PublisherDTOMapper publisherMapper) {
        this.genreMapper = genreMapper;
        this.publisherMapper = publisherMapper;
    }

    public AlbumResponseDTO toDto(AlbumEntity entity) {
        if (entity == null) return null;

        AlbumResponseDTO dto = new AlbumResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setReleaseYear(entity.getReleaseYear());

        dto.setGenre(genreMapper.toDto(entity.getGenre()));
        dto.setPublisher(publisherMapper.toDto(entity.getPublisher()));

        return dto;
    }

    public void copyToEntity(AlbumRequestDTO dto, AlbumEntity entity) {
        entity.setTitle(dto.getTitle());
        entity.setReleaseYear(dto.getReleaseYear());
        // genre & publisher worden in de SERVICE gekoppeld
    }
}
