package com.vinylWebshop.vinylcollectie.mappers;

import com.vinylWebshop.vinylcollectie.dtos.album.AlbumExtendedResponseDTO;
import com.vinylWebshop.vinylcollectie.entities.AlbumEntity;
import org.springframework.stereotype.Component;

@Component
public class AlbumExtendedDTOMapper {

    private final AlbumDTOMapper albumMapper;
    private final StockDTOMapper stockMapper;
    private final ArtistDTOMapper artistMapper;

    public AlbumExtendedDTOMapper(
            AlbumDTOMapper albumMapper,
            StockDTOMapper stockMapper,
            ArtistDTOMapper artistMapper
    ) {
        this.albumMapper = albumMapper;
        this.stockMapper = stockMapper;
        this.artistMapper = artistMapper;
    }

    public AlbumExtendedResponseDTO toDto(AlbumEntity entity) {
        if (entity == null) return null;

        AlbumExtendedResponseDTO dto = new AlbumExtendedResponseDTO();

        // basis album data
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setReleaseYear(entity.getReleaseYear());
        dto.setGenre(albumMapper.toDto(entity).getGenre());
        dto.setPublisher(albumMapper.toDto(entity).getPublisher());

        // extra lijsten
        dto.setStock(
                entity.getStockItems()
                        .stream()
                        .map(stockMapper::toDto)
                        .toList()
        );

        dto.setArtists(
                entity.getArtists()
                        .stream()
                        .map(artistMapper::toDto)
                        .toList()
        );

        return dto;
    }
}
