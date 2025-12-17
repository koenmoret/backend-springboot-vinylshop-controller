package com.vinylWebshop.vinylcollectie.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists")
public class ArtistEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String biography;

    @ManyToMany(mappedBy = "artists")
    private List<AlbumEntity> albums = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
