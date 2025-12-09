package com.vinylWebshop.vinylcollectie.mappers;

import com.vinylWebshop.vinylcollectie.dtos.publisher.PublisherRequestDTO;
import com.vinylWebshop.vinylcollectie.dtos.publisher.PublisherResponseDTO;
import com.vinylWebshop.vinylcollectie.entities.PublisherEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PublisherDTOMapper zet PublisherEntities om naar DTO's en andersom.
 *
 * Waarom een mapper?
 * ------------------
 * - Een Entity is de database-weergave van de data.
 * - Een DTO is de API-weergave van de data (wat de gebruiker te zien krijgt).
 *
 * We scheiden deze twee lagen omdat:
 * - Entities gevoelige data kunnen bevatten (nooit direct teruggeven!)
 * - Entities technische JPA-annotaties hebben die niet relevant zijn voor API-gebruikers
 * - Je API stabiel blijft, zelfs als je database verandert
 * - RequestDTO's alleen de velden bevatten die een gebruiker mág invullen
 */
@Component  // Zorgt dat Spring dit als bean kan injecteren in services
public class PublisherDTOMapper implements DTOMapper<PublisherResponseDTO, PublisherRequestDTO, PublisherEntity> {

    /**
     * Zet één PublisherEntity om naar één PublisherResponseDTO.
     *
     * Wordt gebruikt bij:
     * - GET /publishers/{id}
     * - GET /publishers
     *
     * @param publisher de entity uit de database
     * @return DTO die aan de API wordt teruggegeven
     */
    @Override
    public PublisherResponseDTO mapToDto(PublisherEntity publisher) {

        // Maak een nieuwe ResponseDTO waarin we alleen de relevante gegevens stoppen.
        PublisherResponseDTO dto = new PublisherResponseDTO();

        // Zet de velden uit de Entity over naar het Response DTO
        dto.setId(publisher.getId());
        dto.setName(publisher.getName());
        dto.setAddress(publisher.getAddress());
        dto.setContactDetails(publisher.getContactDetails());

        // Geef het DTO terug — dit gaat naar de controller en uiteindelijk naar de gebruiker
        return dto;
    }

    /**
     * Zet een lijst van PublisherEntities om naar een lijst PublisherResponseDTO's.
     *
     * Streams uitleg:
     * - publishers.stream() maakt een stream van de lijst
     * - map(this::mapToDto) voert mapToDto() uit op elk element
     * - collect(...) zet het resultaat weer om in een lijst
     *
     * @param publishers lijst met PublisherEntity-objecten
     * @return lijst PublisherResponseDTO's
     */
    @Override
    public List<PublisherResponseDTO> mapToDto(List<PublisherEntity> publishers) {
        return publishers.stream()
                .map(this::mapToDto) // gebruik de methode hierboven voor elk item
                .collect(Collectors.toList()); // maak er een List van
    }

    /**
     * Zet een PublisherRequestDTO om naar een PublisherEntity.
     *
     * Wordt gebruikt bij:
     * - POST /publishers (nieuwe publisher aanmaken)
     * - PUT /publishers/{id} (bestaande publisher updaten)
     *
     * De RequestDTO bevat alleen de velden die een client mag doorgeven.
     * Hiermee voorkomen we dat een client bijvoorbeeld zelf een ID of andere
     * interne velden kan meegeven.
     *
     * @param dto de binnenkomende data van de gebruiker
     * @return een PublisherEntity die klaar is voor opslag in de database
     */
    @Override
    public PublisherEntity mapToEntity(PublisherRequestDTO dto) {

        // Maak een nieuwe entity
        PublisherEntity entity = new PublisherEntity();

        // Kopieer alleen de velden die de gebruiker mag invullen
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setContactDetails(dto.getContactDetails());

        // Geef de entity terug aan de service, zodat deze kan worden opgeslagen
        return entity;
    }
}
