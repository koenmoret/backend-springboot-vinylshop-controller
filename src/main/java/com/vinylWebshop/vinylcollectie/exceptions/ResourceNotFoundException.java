package com.vinylWebshop.vinylcollectie.exceptions;

/**
 * Simpele runtime-exception voor 404 Not Found.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) { super(message); }
}
