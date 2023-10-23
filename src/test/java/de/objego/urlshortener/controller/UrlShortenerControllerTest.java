package de.objego.urlshortener.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.fail;

@WebMvcTest
class UrlShortenerControllerTest {

    // TODO: Implement tests
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldShortenURL() {
        fail("Implement me");
    }
}
