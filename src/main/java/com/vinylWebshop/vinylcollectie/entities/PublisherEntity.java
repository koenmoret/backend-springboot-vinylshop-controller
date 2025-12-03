package com.vinylWebshop.vinylcollectie.entities;

import jakarta.persistence.*;

/**
 * PublisherEntity stelt een platenmaatschappij of uitgever voor.
 * Deze klasse wordt als aparte tabel in de database opgeslagen.
 *
 * Velden:
 * - id (overgeërfd van BaseEntity): uniek nummer (primaire sleutel)
 * - createDate (BaseEntity): aanmaakdatum
 * - editDate (BaseEntity): wijzigingsdatum
 * - name: de naam van de uitgever (verplicht)
 * - address: het adres van de uitgever (optioneel)
 * - contactDetails: extra contactinformatie zoals telefoon of e-mail (optioneel)
 */
@Entity // Geeft aan dat deze klasse een entiteit is voor de database.
@Table(name = "publishers") // Zet de tabelnaam op 'publishers' in de database.
public class PublisherEntity extends BaseEntity {

    /**
     * De naam van de uitgeverij, bijvoorbeeld "EMI Records".
     * Dit veld is verplicht (mag niet leeg zijn).
     */
    @Column(nullable = false)
    private String name;

    /**
     * Optioneel: het adres van de uitgever.
     */
    private String address;

    /**
     * Optioneel: aanvullende contactgegevens, zoals telefoonnummer of e-mailadres.
     */
    private String contactDetails;

    /**
     * Lege constructor nodig voor JPA/Hibernate om objecten te kunnen maken.
     */
    public PublisherEntity() {}

    /**
     * Constructor waarmee je direct naam, adres en contactgegevens kunt instellen.
     */
    public PublisherEntity(String name, String address, String contactDetails) {
        this.name = name;
        this.address = address;
        this.contactDetails = contactDetails;
    }

    // ------- Getters en setters --------

    /**
     * Retourneert de naam van de uitgever.
     */
    public String getName() { return name; }

    /**
     * Stelt de naam van de uitgever in.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Retourneert het adres van de uitgever.
     */
    public String getAddress() { return address; }

    /**
     * Stelt het adres van de uitgever in.
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Retourneert de contactgegevens van de uitgever.
     */
    public String getContactDetails() { return contactDetails; }

    /**
     * Stelt de contactgegevens van de uitgever in.
     */
    public void setContactDetails(String contactDetails) { this.contactDetails = contactDetails; }
}
