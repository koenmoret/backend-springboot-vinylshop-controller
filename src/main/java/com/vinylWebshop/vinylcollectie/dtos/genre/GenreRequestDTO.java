package com.vinylWebshop.vinylcollectie.dtos.genre;


/**
 * | Package                 | Gebruik je voor                     | Voorbeeld             |
 * | ----------------------- | ----------------------------------- | --------------------- |
 * | **jakarta.validation**  | Controle van inkomende data (DTO’s) | @NotBlank, @Size      |
 * | **jakarta.persistence** | Database mapping (Entities)         | @Entity, @Id, @Column |
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request DTO voor het aanmaken of updaten van een Genre.
 * Deze DTO zorgt ervoor dat inkomende data van de klant gevalideerd wordt.
 */
public class GenreRequestDTO {

    @NotBlank(message = "Genre name mag niet leeg zijn.")
    @Size(
            min = 2,
            max = 100,
            message = "Genre name moet tussen de 2 en 100 karakters lang zijn."
    )
    private String name;

    @Size(
            max = 255,
            message = "Description mag maximaal 255 karakters lang zijn."
    )
    private String description;


    // Getters & setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
