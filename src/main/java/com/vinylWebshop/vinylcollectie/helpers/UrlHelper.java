package com.vinylWebshop.vinylcollectie.helpers;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class UrlHelper {

    public static URI buildLocationUri(Long id, String path) {
        // Bijvoorbeeld: path = "/genres/"
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(path + "{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
