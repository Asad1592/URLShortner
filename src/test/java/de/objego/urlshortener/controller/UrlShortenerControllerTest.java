package de.objego.urlshortener.controller;

import de.objego.urlshortener.service.UrlShortenerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for {@link UrlShortenerController}. This class contains methods to test
 * encoding and decoding functionalities provided by the controller.
 */
class UrlShortenerControllerTest {

    @InjectMocks
    private UrlShortenerController urlShortenerController;

    @Mock
    private UrlShortenerService urlShortenerService;

    /**
     * Initializes mocks before each test execution.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test to ensure the encode endpoint is working as expected and
     * returns a short URL key for a given long URL.
     */
    @Test
    void shouldEncodeUrl() {
        String longUrl = "https://www.example.com";
        String shortUrlKey = "abc123";

        when(urlShortenerService.encode(longUrl)).thenReturn(shortUrlKey);

        ResponseEntity<String> response = urlShortenerController.encode(longUrl);
        assertEquals(shortUrlKey, response.getBody());
    }

    /**
     * Test to ensure the decode endpoint is working as expected and
     * returns the original long URL for a given short URL key.
     */
    @Test
    void shouldDecodeUrl() {
        String shortUrlKey = "abc123";
        String longUrl = "https://www.example.com";

        when(urlShortenerService.decode(shortUrlKey)).thenReturn(longUrl);

        ResponseEntity<String> response = urlShortenerController.decode(shortUrlKey);
        assertEquals(longUrl, response.getBody());
    }

    /**
     * Test to ensure the decode endpoint returns a not found status
     * when provided with an invalid short URL key.
     */
    @Test
    void shouldReturnNotFoundForInvalidShortUrlKey() {
        String shortUrlKey = "invalidKey";

        when(urlShortenerService.decode(shortUrlKey)).thenReturn(null);

        ResponseEntity<String> response = urlShortenerController.decode(shortUrlKey);
        assertEquals(404, response.getStatusCode().value());
    }
}
