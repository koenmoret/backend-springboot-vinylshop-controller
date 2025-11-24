package com.vinylWebshop.vinylcollectie.controllers;

import com.vinylWebshop.vinylcollectie.entities.GenreEntity;
import com.vinylWebshop.vinylcollectie.services.GenreService;
import org.springframework.web.bind.annotation.*;

@RestController                     // ➤ Dit maakt het een REST-controller
@RequestMapping("/genres")          // ➤ Alle endpoints beginnen met /genres
public class GenreController {

    private final GenreService genreService;

    // ➤ Constructor injection (verplicht in deze opdracht)
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // Vanaf stap 5 gaan we hier de CRUD-methodes toevoegen
}
