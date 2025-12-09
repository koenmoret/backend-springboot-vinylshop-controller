package com.vinylWebshop.vinylcollectie.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * BaseEntity bevat gedeelde velden voor alle entiteiten:
 * - id: automatisch gegenereerde primaire sleutel
 * - createDate: aanmaakdatum
 * - editDate: wijzigingsdatum
 *
 * Dankzij @MappedSuperclass krijgt elke sub-entiteit deze velden,
 * zonder dat BaseEntity zelf een tabel wordt.
 */
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * Primaire sleutel. Wordt automatisch door de database gegenereerd.
     * Geen setter → ID wordt nooit handmatig door code gezet.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Datum waarop het record is aangemaakt.
     */
    private LocalDateTime createDate;

    /**
     * Datum waarop het record voor het laatst is gewijzigd.
     */
    private LocalDateTime editDate;

    /**
     * Wordt aangeroepen vóór INSERT.
     * Zet createDate + editDate automatisch.
     */
    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
        this.editDate = LocalDateTime.now();
    }

    /**
     * Wordt aangeroepen vóór UPDATE.
     * Update alleen de editDate.
     */
    @PreUpdate
    protected void onUpdate() {
        this.editDate = LocalDateTime.now();
    }

    // ----------- Alleen getters (geen setter voor ID!) -----------

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getEditDate() {
        return editDate;
    }

    // Deze setters zijn OK voor datumvelden — JPA gebruikt deze indien nodig.
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setEditDate(LocalDateTime editDate) {
        this.editDate = editDate;
    }
}
