package com.vinylWebshop.vinylcollectie.exceptions;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(Long id) {
        super("Genre met id " + id + " bestaat niet.");
    }
}
