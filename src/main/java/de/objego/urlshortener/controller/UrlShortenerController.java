package de.objego.urlshortener.controller;

import de.objego.urlshortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlShortenerController {

    private final UrlShortenerService shortenerService;

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
