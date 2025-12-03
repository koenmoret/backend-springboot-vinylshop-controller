package com.vinylWebshop.vinylcollectie.services;

import com.vinylWebshop.vinylcollectie.entities.GenreEntity;
import com.vinylWebshop.vinylcollectie.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviceklasse voor GenreEntity.
 * Hier zet je alle logica die te maken heeft met genres beheren.
 */
@Service
public class GenreService {

    private final GenreRepository genreRepository;

    /**
     * Constructor-injectie van de repository.
     * Spring zorgt zelf voor het juiste object.
     */
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * Haal alle genres op uit de database.
     */
    public List<GenreEntity> findAllGenres() {
        return genreRepository.findAll();
    }

    /**
     * Haal één genre op op basis van zijn id.
     * Als het genre niet bestaat, returnt de methode null.
     */
    public GenreEntity findGenreById(Long id) {
        // Optioneel: je kunt hier ook een exception gooien of een Optional teruggeven.
        return genreRepository.findById(id).orElse(null);
    }

    /**
     * Maak een nieuw genre aan en sla deze op in de database.
     */
    public GenreEntity createGenre(GenreEntity input) {
        // Het id wordt automatisch gevuld door de database. (Input.id mag null zijn.)
        return genreRepository.save(input);
    }

    /**
     * Werk een bestaand genre bij (op basis van id).
     * Alleen de naam en omschrijving kunnen gewijzigd worden.
     * Als het genre niet bestaat, returnt deze methode null.
     */
    public GenreEntity updateGenre(Long id, GenreEntity input) {
        GenreEntity existing = getGenreById(id); // Private helper
        if (existing == null) {
            return null; // Kan eventueel vervangen worden door een exception
        }
        existing.setName(input.getName());
        existing.setDescription(input.getDescription());
        // id, createDate en editDate worden niet handmatig aangepast!
        return genreRepository.save(existing);
    }

    /**
     * Verwijder een genre op basis van zijn id.
     * Als het genre niet bestaat, gebeurt er niks.
     */
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    /**
     * Private helper-methode om een genre veilig op te halen.
     * Handig om dubbele code te voorkomen.
     */
    private GenreEntity getGenreById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }
}
