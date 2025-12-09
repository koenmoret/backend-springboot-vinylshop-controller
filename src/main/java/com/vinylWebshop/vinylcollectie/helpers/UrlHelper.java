package com.vinylWebshop.vinylcollectie.helpers;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

/**
 * UrlHelper is een hulpfunctie om correcte 'Location' headers te bouwen bij POST-verzoeken.
 * Dit helpt REST-clients makkelijk het nieuwe resource-adres te vinden na een POST.
 */

@Component
public class UrlHelper {

    /**
     * Bouwt een volledige URI voor het nieuw aangemaakte object.
     *
     * @param id   Het id van het nieuwe object (bijvoorbeeld 5)
     * @param path Het basispad van de resource (bijvoorbeeld "/genres/")
     * @return De volledige URI, bijvoorbeeld "http://localhost:8080/genres/5"
     *
     * Voorbeeldgebruik in controller:
     *    URI location = UrlHelper.getCurrentUrlWithId(createdGenre.getId(), "/genres/");
     */
    public URI getCurrentUrlWithId(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

}
