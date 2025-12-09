package com.vinylWebshop.vinylcollectie.dtos.publisher;

/**
 * Dit is een Response DTO (Data Transfer Object).
 * Deze klasse bepaalt welke data je teruggeeft aan de client
 * wanneer iemand via de API een Publisher opvraagt.
 *
 * Belangrijk:
 * - Je geeft NOOIT de hele Entity direct terug (om privacy en structuur-redenen).
 * - Je bepaalt hier precies welke velden de API mag laten zien.
 */
public class PublisherResponseDTO {

    // Het unieke ID van de publisher (komt overeen met het id in de database)
    private Long id;

    // De naam van de uitgever (publisher)
    private String name;

    // Het adres van de uitgever
    private String address;

    // Extra contactinformatie (telefoonnummer, e-mail, website, etc.)
    private String contactDetails;


    // Getter voor id:
    // Hiermee kan de buitenwereld (controller/service) het id opvragen.
    public Long getId() {
        return id;
    }

    // Setter voor id:
    // Hiermee kunnen we het id zetten vanuit de service of mapper.
    public void setId(Long id) {
        this.id = id;
    }

    // Getter voor name
    public String getName() {
        return name;
    }

    // Setter voor name
    public void setName(String name) {
        this.name = name;
    }

    // Getter voor address
    public String getAddress() {
        return address;
    }

    // Setter voor address
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter voor contactDetails
    public String getContactDetails() {
        return contactDetails;
    }

    // Setter voor contactDetails
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}
