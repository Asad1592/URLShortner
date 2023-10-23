package de.objego.urlshortener.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class UrlShortenerService {

    private final ConcurrentHashMap<String, String> urlMapping = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();
    private final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String encode(String longUrl) {
        long id = idGenerator.incrementAndGet();  // Generate a unique ID
        String encoded = base62Encode(id);
        urlMapping.put(encoded, longUrl);  // Storing the mapping from the short URL to the long URL
        log.info("Encoded URL: {} to Short URL Key: {}", longUrl, encoded);
        return encoded;
    }

    public String decode(String shortUrlKey) {
        String longUrl = urlMapping.get(shortUrlKey);

        if(longUrl == null) {
            log.warn("Short URL Key not found: {}", shortUrlKey);
        } else {
            log.info("Decoded Short URL Key: {} to Long URL: {}", shortUrlKey, longUrl);
        }

        return longUrl;
    }

    private String base62Encode(long value) {
        StringBuilder encoded = new StringBuilder();
        while (value != 0) {
            encoded.append(BASE62.charAt((int) (value % 62)));
            value /= 62;
        }
        return encoded.reverse().toString();  // Return the base62 encoded ID as the short URL
    }
}
