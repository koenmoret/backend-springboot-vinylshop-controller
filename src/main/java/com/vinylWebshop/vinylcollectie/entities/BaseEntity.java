package com.vinylWebshop.vinylcollectie.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * BaseEntity is een abstracte klasse die gedeelde velden bevat voor alle entiteiten:
 * - id: unieke sleutel (primaire key)
 * - createDate: aanmaakdatum van het record
 * - editDate: laatste wijzigingsdatum van het record
 *
 * Door @MappedSuperclass krijgen subklassen deze velden, zonder dat BaseEntity zelf een tabel krijgt.
 */
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * Het unieke id voor elk record.
     * Wordt automatisch gegenereerd door de database (auto-increment).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Het tijdstip waarop het record voor het eerst is opgeslagen in de database.
     */
    private LocalDateTime createDate;

    /**
     * Het tijdstip waarop het record voor het laatst is bijgewerkt.
     */
    private LocalDateTime editDate;

    /**
     * Deze methode wordt automatisch aangeroepen door JPA/Hibernate
     * vlak voordat een nieuw record wordt weggeschreven naar de database.
     * Beide datumvelden worden dan gevuld met het huidige tijdstip.
     */
    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
        this.editDate = LocalDateTime.now();
    }

    /**
     * Deze methode wordt automatisch aangeroepen door JPA/Hibernate
     * vlak voordat een bestaand record wordt bijgewerkt (update).
     * Alleen het editDate-veld wordt dan geüpdatet naar het huidige tijdstip.
     */
    @PreUpdate
    protected void onUpdate() {
        this.editDate = LocalDateTime.now();
    }

    // ----------- Getters en setters -----------

    /**
     * Retourneert het id van het record.
     */
    public Long getId() { return id; }

    /**
     * Retourneert de aanmaakdatum van het record.
     */
    public LocalDateTime getCreateDate() { return createDate; }

    /**
     * Retourneert de laatste wijzigingsdatum van het record.
     */
    public LocalDateTime getEditDate() { return editDate; }

    /**
     * Stelt het id van het record in.
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Stelt de aanmaakdatum in.
     */
    public void setCreateDate(LocalDateTime createDate) { this.createDate = createDate; }

    /**
     * Stelt de wijzigingsdatum in.
     */
    public void setEditDate(LocalDateTime editDate) { this.editDate = editDate; }
}
