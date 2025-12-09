package com.vinylWebshop.vinylcollectie.mappers;

import com.vinylWebshop.vinylcollectie.entities.BaseEntity;
import java.util.List;

/**
 * Algemene Mapper-interface voor alle DTO-mappers binnen het project.
 *
 * Waarom deze interface?
 * ----------------------
 * In plaats van voor elk type Entity dezelfde methodes opnieuw te moeten schrijven,
 * leggen we hier één keer vast wat elke Mapper MOET kunnen:
 *
 * 1. Een Entity omzetten naar een Response DTO
 * 2. Een lijst van Entities omzetten naar een lijst van Response DTO’s
 * 3. Een Request DTO omzetten naar een Entity (bij POST of PUT requests)
 *
 * Door generics te gebruiken (<RESPONSE, REQUEST, T extends BaseEntity>)
 * kan deze interface werken voor elk soort Entity en DTO-combinatie
 * zonder duplicatie van code.
 *
 * Uitleg van de generics:
 * -----------------------
 * RESPONSE → het type Response DTO (bijvoorbeeld GenreResponseDTO)
 * REQUEST  → het type Request DTO  (bijvoorbeeld GenreRequestDTO)
 * T        → het type Entity dat gemapt wordt (bijvoorbeeld GenreEntity)
 *
 * T extends BaseEntity betekent:
 *   - T moet een subclass zijn van BaseEntity
 *   - Dus: elke mapper werkt verplicht met een echte database-entity
 *
 * Elke mapper die deze interface implementeert:
 *   @Override
 *   public GenreResponseDTO mapToDto(GenreEntity model)
 *   → krijgt een vaste structuur en dezelfde methodenamen.
 */
public interface DTOMapper<RESPONSE, REQUEST, T extends BaseEntity> {

    /**
     * Zet één Entity om naar één Response DTO.
     *
     * Bijvoorbeeld:
     *   - GenreEntity → GenreResponseDTO
     *   - PublisherEntity → PublisherResponseDTO
     *
     * @param model De Entity uit de database
     * @return RESPONSE Het Response DTO dat naar de API wordt gestuurd
     */
    RESPONSE mapToDto(T model);

    /**
     * Zet een lijst Entities om naar een lijst Response DTO's.
     *
     * Dit wordt gebruikt bij GET requests zoals:
     *   GET /genres
     *
     * @param models Een lijst entities uit de database
     * @return List<RESPONSE> Een lijst DTO’s voor de API response
     */
    List<RESPONSE> mapToDto(List<T> models);

    /**
     * Zet een Request DTO om naar een Entity.
     *
     * Dit wordt gebruikt bij:
     *   POST /genres
     *   PUT  /genres/{id}
     *
     * @param requestModel Het Request DTO met binnenkomende user data
     * @return T De Entity die naar de service en repository gaat
     */
    T mapToEntity(REQUEST requestModel);
}
