package com.vinylWebshop.vinylcollectie.repositories;

/**
* In Spring Boot/JPA zijn repositories de brug tussen je Java-code en de database.
* Met één interface heb je automatisch alle standaard CRUD-methoden (save, findAll, findById, deleteById, etc.), zonder zelf SQL te schrijven.
*/


import com.vinylWebshop.vinylcollectie.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository voor het GenreEntity-model.
 * Deze interface zorgt automatisch voor alle standaard database-bewerkingen.
 * <GenreEntity, Long> betekent: de entiteit en het type van de primary key.
 */
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    // Geen extra code nodig: standaardmethodes zoals findAll(), findById(), save(), deleteById() zijn al beschikbaar.
}
