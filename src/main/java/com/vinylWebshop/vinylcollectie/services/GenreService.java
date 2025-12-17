package com.vinylWebshop.vinylcollectie.services;

import com.vinylWebshop.vinylcollectie.dtos.genre.GenreRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.genre.GenreResponseDTO;
import com.vinylWebshop.vinylcollectie.entities.AlbumEntity;
import com.vinylWebshop.vinylcollectie.entities.GenreEntity;
import com.vinylWebshop.vinylcollectie.exceptions.ResourceNotFoundException;
import com.vinylWebshop.vinylcollectie.mappers.GenreDTOMapper;
import com.vinylWebshop.vinylcollectie.repositories.AlbumRepository;
import com.vinylWebshop.vinylcollectie.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final AlbumRepository albumRepository;
    private final GenreDTOMapper genreMapper;

    public GenreService(GenreRepository genreRepository, AlbumRepository albumRepository, GenreDTOMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.albumRepository = albumRepository;
        this.genreMapper = genreMapper;
    }

    public List<GenreResponseDTO> getAll() {
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::toDto)
                .toList();
    }

    public GenreResponseDTO getById(Long id) {
        GenreEntity entity = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Genre %d niet gevonden".formatted(id)
                ));
        return genreMapper.toDto(entity);
    }

    public GenreResponseDTO create(GenreRequestDTO request) {
        GenreEntity entity = new GenreEntity();
        genreMapper.copyToEntity(request, entity);

        GenreEntity saved = genreRepository.save(entity);
        return genreMapper.toDto(saved);
    }

    public GenreResponseDTO update(Long id, GenreRequestDTO request) {
        GenreEntity entity = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Genre %d niet gevonden".formatted(id)
                ));

        genreMapper.copyToEntity(request, entity);
        GenreEntity saved = genreRepository.save(entity);
        return genreMapper.toDto(saved);
    }

    public void delete(Long id) {
        GenreEntity genre = genreRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Genre %d niet gevonden".formatted(id)));

        // Relatie verbreken (unidirectioneel)
        List<AlbumEntity> albums = albumRepository.findByGenre_Id(id);
        for (AlbumEntity album : albums) {
            album.setGenre(null);
            albumRepository.save(album);
        }

        // Genre verwijderen
        genreRepository.delete(genre);
    }

}
