package de.objego.urlshortener.controller;

import de.objego.urlshortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlShortenerController {
    private final UrlShortenerService shortenerService;

    // TODO: Implement the REST endpoints here
}
