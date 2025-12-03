package com.vinylWebshop.vinylcollectie.helpers;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

/**
 * UrlHelper is een hulpfunctie om correcte 'Location' headers te bouwen bij POST-verzoeken.
 * Dit helpt REST-clients makkelijk het nieuwe resource-adres te vinden na een POST.
 */
public class UrlHelper {

    /**
     * Bouwt een volledige URI voor het nieuw aangemaakte object.
     *
     * @param id   Het id van het nieuwe object (bijvoorbeeld 5)
     * @param path Het basispad van de resource (bijvoorbeeld "/genres/")
     * @return De volledige URI, bijvoorbeeld "http://localhost:8080/genres/5"
     *
     * Voorbeeldgebruik in controller:
     *    URI location = UrlHelper.buildLocationUri(createdGenre.getId(), "/genres/");
     */
    public static URI buildLocationUri(Long id, String path) {
        // fromCurrentContextPath() pakt de basis van de huidige applicatie (bv. http://localhost:8080)
        // .path(path + "{id}") vult het id in het pad in
        // buildAndExpand(id) vervangt {id} met de werkelijke waarde
        // .toUri() zet het om in een URI object
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(path + "{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
