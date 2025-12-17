package com.vinylWebshop.vinylcollectie.repositories;

import com.vinylWebshop.vinylcollectie.entities.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {
    // geen custom queries nodig
}
