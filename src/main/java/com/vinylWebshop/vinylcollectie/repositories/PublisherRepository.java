package com.vinylWebshop.vinylcollectie.repositories;

/**
 * In Spring Boot/JPA zijn repositories de brug tussen je Java-code en de database.
 * Met één interface heb je automatisch alle standaard CRUD-methoden (save, findAll, findById, deleteById, etc.), zonder zelf SQL te schrijven.
 */

import com.vinylWebshop.vinylcollectie.entities.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository voor het PublisherEntity-model.
 * Biedt standaard CRUD-operaties zonder extra code.
 */
public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {
    // Geen extra code nodig: standaardmethodes zijn al beschikbaar.
}
