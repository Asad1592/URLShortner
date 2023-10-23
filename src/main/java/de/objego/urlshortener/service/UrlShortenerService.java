package de.objego.urlshortener.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class UrlShortenerService {

    private final ConcurrentHashMap<String, String> urlMapping = new ConcurrentHashMap<>();
    private final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String encode(String longUrl) {
        String shortUrlKey = base62Encode(longUrl.hashCode());
        urlMapping.put(shortUrlKey, longUrl);
        return shortUrlKey;
    }

    public String decode(String shortUrlKey) {
        return urlMapping.get(shortUrlKey);
    }

    private String base62Encode(int value) {
        StringBuilder encoded = new StringBuilder();
        while (value != 0) {
            encoded.append(BASE62.charAt(value % 62));
            value /= 62;
        }
        return encoded.reverse().toString();
    }
}
