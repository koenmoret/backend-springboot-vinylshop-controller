package com.vinylWebshop.vinylcollectie.dtos.publisher;

/**
 * | Package                 | Gebruik je voor                     | Voorbeeld             |
 * | ----------------------- | ----------------------------------- | --------------------- |
 * | **jakarta.validation**  | Controle van inkomende data (DTO’s) | @NotBlank, @Size      |
 * | **jakarta.persistence** | Database mapping (Entities)         | @Entity, @Id, @Column |
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request DTO voor het aanmaken of updaten van een Publisher.
 * Valideert de binnenkomende data voordat deze naar de service wordt gestuurd.
 */
public class PublisherRequestDTO {

    @NotBlank(message = "Publisher name mag niet leeg zijn.")
    @Size(
            max = 50,
            message = "Publisher name mag maximaal 50 karakters lang zijn."
    )
    private String name;

    private String address;

    private String contactDetails;


    // Getters & setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}
