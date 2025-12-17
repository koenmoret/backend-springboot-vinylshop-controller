package com.vinylWebshop.vinylcollectie.mappers;

import com.vinylWebshop.vinylcollectie.dtos.genre.GenreRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.genre.GenreResponseDTO;
import com.vinylWebshop.vinylcollectie.entities.GenreEntity;
import org.springframework.stereotype.Component;

@Component
public class GenreDTOMapper {

    public GenreResponseDTO toDto(GenreEntity entity) {
        if (entity == null) return null;

        GenreResponseDTO dto = new GenreResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public void copyToEntity(GenreRequestDTO dto, GenreEntity entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
    }
}
