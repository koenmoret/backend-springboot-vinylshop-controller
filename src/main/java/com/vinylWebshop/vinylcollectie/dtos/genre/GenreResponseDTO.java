package com.vinylWebshop.vinylcollectie.dtos.genre;

/**
 * Dit is een Response DTO (Data Transfer Object) voor het Genre.
 * Een DTO bepaalt welke gegevens we teruggeven via de API.
 *
 * Waarom een DTO?
 * - We willen nooit de volledige Entity teruggeven (veiligheid & structuur).
 * - We bepalen per endpoint welke velden zichtbaar zijn.
 * - Dit maakt je API duidelijker en onafhankelijk van je database-model.
 */
public class GenreResponseDTO {

    // Het unieke ID van het genre — komt overeen met het primary key veld in de database.
    private Long id;

    // De naam van het genre (bijv. Rock, Pop, Jazz).
    private String name;

    // Een korte beschrijving van dit genre.
    private String description;


    // Getter voor id — hiermee kan de controller of service het ID uitlezen.
    public Long getId() {
        return id;
    }

    // Setter voor id — hiermee vullen we het ID vanuit de mapper of service.
    public void setId(Long id) {
        this.id = id;
    }

    // Getter voor name — haalt de naam van het genre op.
    public String getName() {
        return name;
    }

    // Setter voor name — zet de naam van het genre.
    public void setName(String name) {
        this.name = name;
    }

    // Getter voor description — hierdoor kan de controller de beschrijving teruggeven.
    public String getDescription() {
        return description;
    }

    // Setter voor description — hiermee vullen we de beschrijving in vanuit de mapper of service.
    public void setDescription(String description) {
        this.description = description;
    }
}
