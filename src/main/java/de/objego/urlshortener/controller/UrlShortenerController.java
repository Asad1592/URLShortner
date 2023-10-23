package de.objego.urlshortener.controller;

import de.objego.urlshortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * REST Controller for handling URL shortening and expanding requests.
 */
@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlShortenerController {

    private final UrlShortenerService shortenerService;

    /**
     * Encodes a given long URL to a shortened version.
     *
     * @param longUrl the original long URL to be shortened.
     * @return a shortened URL key wrapped in a ResponseEntity.
     * @throws org.springframework.web.server.ResponseStatusException if the URL is invalid or blank.
     */
    @PostMapping("/encode")
    public ResponseEntity<String> encode(@RequestBody String longUrl) {
        if (longUrl == null || longUrl.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid URL");
        }
        try {
            new URL(longUrl); // Validate the URL
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid URL");
        }
        String shortUrlKey = shortenerService.encode(longUrl);
        return ResponseEntity.ok(shortUrlKey);
    }

    /**
     * Decodes a shortened URL key to its original long URL.
     *
     * @param shortUrlKey the shortened URL key to be decoded.
     * @return the original long URL wrapped in a ResponseEntity, or NotFound status if the key is invalid.
     */
    @GetMapping("/decode/{shortUrlKey}")
    public ResponseEntity<String> decode(@PathVariable String shortUrlKey) {
        String longUrl = shortenerService.decode(shortUrlKey);
        if (longUrl != null) {
            return ResponseEntity.ok(longUrl);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
