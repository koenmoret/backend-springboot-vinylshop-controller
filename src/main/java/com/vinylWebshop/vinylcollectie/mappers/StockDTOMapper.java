package com.vinylWebshop.vinylcollectie.mappers;

import com.vinylWebshop.vinylcollectie.dtos.stock.StockRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.stock.StockResponseDTO;
import com.vinylWebshop.vinylcollectie.entities.StockEntity;
import org.springframework.stereotype.Component;

@Component
public class StockDTOMapper {

    public StockResponseDTO toDto(StockEntity entity) {
        if (entity == null) return null;

        StockResponseDTO dto = new StockResponseDTO();
        dto.setId(entity.getId());
        dto.setCondition(entity.getCondition());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public void copyToEntity(StockRequestDTO dto, StockEntity entity) {
        entity.setCondition(dto.getCondition());
        entity.setPrice(dto.getPrice());
    }
}
