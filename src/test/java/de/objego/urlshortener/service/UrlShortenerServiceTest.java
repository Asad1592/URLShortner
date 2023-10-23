package de.objego.urlshortener.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link UrlShortenerService}. This class contains methods to test
 * the encoding and decoding functionalities provided by the service.
 */
class UrlShortenerServiceTest {

    private UrlShortenerService urlShortenerService;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        urlShortenerService = new UrlShortenerService();
    }

    /**
     * Test to ensure a long URL can be encoded to a short URL key and then
     * decoded back to the original long URL accurately.
     */
    @Test
    void shouldEncodeAndDecodeUrl() {
        String longUrl = "https://www.example.com";
        String shortUrlKey = urlShortenerService.encode(longUrl);
        assertNotNull(shortUrlKey);
        assertFalse(shortUrlKey.isBlank());

        String decodedUrl = urlShortenerService.decode(shortUrlKey);
        assertEquals(longUrl, decodedUrl);
    }

    /**
     * Test to ensure that attempting to decode a non-existent short URL key
     * returns null.
     */
    @Test
    void shouldReturnNullForNonExistentKey() {
        String decodedUrl = urlShortenerService.decode("nonExistentKey");
        assertNull(decodedUrl);
    }
}
