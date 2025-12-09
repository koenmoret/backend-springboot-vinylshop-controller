package com.vinylWebshop.vinylcollectie.mappers;

import com.vinylWebshop.vinylcollectie.dtos.genre.GenreRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.genre.GenreResponseDTO;
import com.vinylWebshop.vinylcollectie.entities.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * GenreDTOMapper is verantwoordelijk voor het omzetten (mappen)
 * van Entity → DTO en van DTO → Entity.
 *
 * Waarom hebben we deze mapper?
 * -----------------------------
 * - Je wilt nooit direct een Entity teruggeven aan de client (veiligheid, structuur).
 * - Je wilt nooit direct een Request DTO opslaan als Entity (afzondering van lagen).
 *
 * Daarom gebruiken we mappers om data netjes te vertalen tussen de lagen:
 * Controller → DTO → Mapper → Entity → Service → Repository → Database
 * en terug.
 */
@Component // Spring maakt automatisch een bean aan zodat we deze mapper kunnen injecteren in services.
public class GenreDTOMapper implements DTOMapper<GenreResponseDTO, GenreRequestDTO, GenreEntity> {

    /**
     * Zet één GenreEntity om naar één GenreResponseDTO.
     *
     * Dit wordt o.a. gebruikt bij:
     * - GET /genres/{id}
     * - GET /genres
     *
     * @param model de Entity uit de database
     * @return het DTO dat naar de frontend gestuurd wordt
     */
    @Override
    public GenreResponseDTO mapToDto(GenreEntity model) {

        // Maak een nieuw Response DTO
        GenreResponseDTO dto = new GenreResponseDTO();

        // Vul de DTO met de waardes uit de Entity
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());

        // Geef het DTO terug aan de controller/service
        return dto;
    }

    /**
     * Zet een lijst GenreEntities om naar een lijst GenreResponseDTO's.
     *
     * Waarom?
     * GET /genres → geeft ALLE genres terug in een lijst.
     *
     * Streams uitleg:
     * - models.stream() → maak er een stream van
     * - map(this::mapToDto) → voor elk element roepen we mapToDto(entity) aan
     * - collect(...) → maak er weer een lijst van
     */
    @Override
    public List<GenreResponseDTO> mapToDto(List<GenreEntity> models) {
        return models.stream()
                .map(this::mapToDto) // gebruik de methode hierboven voor elke entity
                .collect(Collectors.toList()); // zet het terug naar een List
    }

    /**
     * Zet een GenreRequestDTO om naar een GenreEntity.
     *
     * Wanneer wordt dit gebruikt?
     * - POST /genres   → nieuw genre maken
     * - PUT /genres/{id} → bestaand genre updaten
     *
     * De user stuurt JSON → Controller maakt DTO → Mapper maakt Entity → Service slaat op.
     */
    @Override
    public GenreEntity mapToEntity(GenreRequestDTO genreModel) {

        // Maak een nieuwe Entity
        GenreEntity entity = new GenreEntity();

        // Kopieer de waarde van DTO naar Entity
        entity.setName(genreModel.getName());
        entity.setDescription(genreModel.getDescription());

        // Deze entity gaat straks naar de repository om opgeslaan te worden
        return entity;
    }
}
