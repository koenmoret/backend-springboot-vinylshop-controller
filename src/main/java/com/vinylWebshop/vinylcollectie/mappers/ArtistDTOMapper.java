package com.vinylWebshop.vinylcollectie.mappers;

import com.vinylWebshop.vinylcollectie.dtos.artist.ArtistRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.artist.ArtistResponseDTO;
import com.vinylWebshop.vinylcollectie.entities.ArtistEntity;
import org.springframework.stereotype.Component;

@Component
public class ArtistDTOMapper {

    public ArtistResponseDTO toDto(ArtistEntity entity) {
        if (entity == null) return null;

        ArtistResponseDTO dto = new ArtistResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBiography(entity.getBiography());
        return dto;
    }

    public void copyToEntity(ArtistRequestDTO dto, ArtistEntity entity) {
        entity.setName(dto.getName());
        entity.setBiography(dto.getBiography());
    }
}
