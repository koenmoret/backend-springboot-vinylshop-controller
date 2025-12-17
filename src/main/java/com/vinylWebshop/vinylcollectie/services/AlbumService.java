package com.vinylWebshop.vinylcollectie.services;

import com.vinylWebshop.vinylcollectie.dtos.album.AlbumRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.album.AlbumResponseDTO;
import com.vinylWebshop.vinylcollectie.dtos.album.AlbumExtendedResponseDTO;
import com.vinylWebshop.vinylcollectie.entities.AlbumEntity;
import com.vinylWebshop.vinylcollectie.entities.GenreEntity;
import com.vinylWebshop.vinylcollectie.entities.PublisherEntity;
import com.vinylWebshop.vinylcollectie.exceptions.ResourceNotFoundException;
import com.vinylWebshop.vinylcollectie.mappers.AlbumDTOMapper;
import com.vinylWebshop.vinylcollectie.mappers.AlbumExtendedDTOMapper;
import com.vinylWebshop.vinylcollectie.repositories.AlbumRepository;
import com.vinylWebshop.vinylcollectie.repositories.GenreRepository;
import com.vinylWebshop.vinylcollectie.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;
    private final AlbumRepository albumRepository;
    private final AlbumDTOMapper albumMapper;
    private final AlbumExtendedDTOMapper albumExtendedMapper;

    public AlbumService(
            GenreRepository genreRepository,
            PublisherRepository publisherRepository,
            AlbumRepository albumRepository,
            AlbumDTOMapper albumMapper,
            AlbumExtendedDTOMapper albumExtendedMapper
    ) {
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
        this.albumRepository = albumRepository;
        this.albumMapper = albumMapper;
        this.albumExtendedMapper = albumExtendedMapper;
    }

    /* ===============================
       READ
       =============================== */
    public AlbumExtendedResponseDTO getById(Long id) {
        AlbumEntity album = albumRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Album %d niet gevonden".formatted(id)));

        return albumExtendedMapper.toDto(album);
    }



    public List<AlbumResponseDTO> getAll() {
        return albumRepository.findAll()
                .stream()
                .map(albumMapper::toDto)
                .toList();
    }

    /* ===============================
       CREATE
       =============================== */

    public AlbumResponseDTO createAlbum(AlbumRequestDTO request) {
        AlbumEntity entity = new AlbumEntity();
        albumMapper.copyToEntity(request, entity);

        // Genre is optioneel bij POST
        if (request.getGenreId() != null) {
            GenreEntity genre = getGenreEntity(request.getGenreId());
            entity.setGenre(genre);
        }

        // Publisher is optioneel bij POST
        if (request.getPublisherId() != null) {
            PublisherEntity publisher = getPublisherEntity(request.getPublisherId());
            entity.setPublisher(publisher);
        }

        AlbumEntity saved = albumRepository.save(entity);
        return albumMapper.toDto(saved);
    }

    /* ===============================
       UPDATE (PUT)
       =============================== */

    public AlbumResponseDTO updateAlbum(Long id, AlbumRequestDTO request) {
        AlbumEntity entity = albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Album " + id + " niet gevonden"));

        albumMapper.copyToEntity(request, entity);

        // PUT = volledig object → geen null-checks
        GenreEntity genre = getGenreEntity(request.getGenreId());
        PublisherEntity publisher = getPublisherEntity(request.getPublisherId());

        entity.setGenre(genre);
        entity.setPublisher(publisher);

        AlbumEntity saved = albumRepository.save(entity);
        return albumMapper.toDto(saved);
    }

    /* ===============================
       DELETE
       =============================== */

    public void deleteAlbum(Long id) {
        AlbumEntity album = albumRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Album %d niet gevonden".formatted(id)));

        // Controle: mag alleen verwijderen als er geen stock is
        if (!album.getStockItems().isEmpty()) {
            throw new IllegalStateException(
                    "Album kan niet worden verwijderd zolang er stock aanwezig is"
            );
        }
        albumRepository.delete(album);
    }


    /* ===============================
       HELPER METHODS
       =============================== */

    private GenreEntity getGenreEntity(Long genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Genre " + genreId + " niet gevonden"));
    }

    private PublisherEntity getPublisherEntity(Long publisherId) {
        return publisherRepository.findById(publisherId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Publisher " + publisherId + " niet gevonden"));
    }

    public List<AlbumResponseDTO> getAlbumsWithStock(Boolean stock) {
        List<AlbumEntity> albums;

        if (Boolean.TRUE.equals(stock)) {
            albums = albumRepository.findByStockItemsNotEmpty();
        } else {
            albums = albumRepository.findByStockItemsEmpty();
        }

        return albums.stream()
                .map(albumMapper::toDto)
                .toList();
    }

}
