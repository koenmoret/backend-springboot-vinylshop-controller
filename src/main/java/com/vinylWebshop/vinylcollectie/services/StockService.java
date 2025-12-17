package com.vinylWebshop.vinylcollectie.services;

import com.vinylWebshop.vinylcollectie.dtos.stock.StockRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.stock.StockResponseDTO;
import com.vinylWebshop.vinylcollectie.entities.StockEntity;
import com.vinylWebshop.vinylcollectie.exceptions.ResourceNotFoundException;
import com.vinylWebshop.vinylcollectie.mappers.StockDTOMapper;
import com.vinylWebshop.vinylcollectie.repositories.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final StockDTOMapper stockMapper;

    public StockService(StockRepository stockRepository, StockDTOMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    public List<StockResponseDTO> getAll() {
        return stockRepository.findAll()
                .stream()
                .map(stockMapper::toDto)
                .toList();
    }

    public StockResponseDTO getById(Long id) {
        StockEntity entity = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock %d niet gevonden".formatted(id)));
        return stockMapper.toDto(entity);
    }

    public StockResponseDTO create(StockRequestDTO request) {
        StockEntity entity = new StockEntity();
        stockMapper.copyToEntity(request, entity);

        StockEntity saved = stockRepository.save(entity);
        return stockMapper.toDto(saved);
    }

    public void delete(Long id) {
        if (!stockRepository.existsById(id)) {
            throw new ResourceNotFoundException("Stock %d niet gevonden".formatted(id));
        }
        stockRepository.deleteById(id);
    }
}
