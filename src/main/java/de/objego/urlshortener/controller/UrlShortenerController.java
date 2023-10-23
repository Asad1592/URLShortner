package de.objego.urlshortener.controller;

import de.objego.urlshortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlShortenerController {

    private final UrlShortenerService shortenerService;

    @PostMapping("/encode")
    public ResponseEntity<String> encode(@RequestBody String longUrl) {
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
