package com.vinylWebshop.vinylcollectie.dtos.artist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ArtistRequestDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Size(max = 2000)
    private String biography;

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
