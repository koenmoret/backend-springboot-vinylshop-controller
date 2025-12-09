package com.vinylWebshop.vinylcollectie.entities;

import jakarta.persistence.*;

/**
 * GenreEntity stelt één muziekgenre voor, bijvoorbeeld "Rock" of "Jazz".
 * Deze klasse wordt als aparte tabel in de database opgeslagen.
 * <p>
 * Velden:
 * - id (overgeërfd van BaseEntity): uniek nummer (primaire sleutel)
 * - createDate (BaseEntity): aanmaakdatum
 * - editDate (BaseEntity): wijzigingsdatum
 * - name: de naam van het genre (verplicht)
 * - description: optionele omschrijving van het genre
 */
@Entity // Geeft aan dat deze klasse een "entiteit" is en in de database komt.
@Table(name = "genres") // Zorgt dat de tabel in de database 'genres' heet.
public class GenreEntity extends BaseEntity {

    /**
     * De naam van het genre, bijvoorbeeld "Rock".
     * Deze kolom mag niet leeg zijn (verplicht veld).
     */
    @Column(nullable = false)
    private String name;

    /**
     * Optionele omschrijving van het genre, bijvoorbeeld "Elektrische gitaren, snelle ritmes".
     */
    private String description;

    /**
     * Lege constructor nodig voor JPA/Hibernate om objecten te kunnen maken.
     */
    public GenreEntity() {}

    /**
     * Handige extra constructor waarmee je direct naam en omschrijving kunt zetten.
     */
    public GenreEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // ------- Getters en setters --------

    /**
     * Retourneert de naam van het genre.
     */
    public String getName() { return name; }

    /**
     * Stelt de naam van het genre in.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Retourneert de beschrijving van het genre.
     */
    public String getDescription() { return description; }

    /**
     * Stelt de beschrijving van het genre in.
     */
    public void setDescription(String description) { this.description = description; }
}
