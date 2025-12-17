package com.vinylWebshop.vinylcollectie.repositories;

import com.vinylWebshop.vinylcollectie.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    // geen custom queries nodig
}
