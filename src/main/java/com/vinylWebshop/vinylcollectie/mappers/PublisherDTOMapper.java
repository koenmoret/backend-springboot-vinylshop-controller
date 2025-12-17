package com.vinylWebshop.vinylcollectie.mappers;

import com.vinylWebshop.vinylcollectie.dtos.publisher.PublisherRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.publisher.PublisherResponseDTO;
import com.vinylWebshop.vinylcollectie.entities.PublisherEntity;
import org.springframework.stereotype.Component;

@Component
public class PublisherDTOMapper {

    public PublisherResponseDTO toDto(PublisherEntity entity) {
        if (entity == null) return null;

        PublisherResponseDTO dto = new PublisherResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public void copyToEntity(PublisherRequestDTO dto, PublisherEntity entity) {
        entity.setName(dto.getName());
    }
}
