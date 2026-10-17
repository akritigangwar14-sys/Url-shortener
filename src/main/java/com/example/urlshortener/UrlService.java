package com.example.urlshortener;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    // Method to shorten a long URL
    public String shortenUrl(String originalUrl) {
        // Generate a random 6-character short key using UUID
        String shortKey = UUID.randomUUID().toString().substring(0, 6);

        UrlMapping mapping = new UrlMapping(originalUrl, shortKey);
        urlRepository.save(mapping);

        return shortKey;
    }

    // Method to get the original URL by its short key
    public String getOriginalUrl(String shortKey) {
        UrlMapping mapping = urlRepository.findByShortKey(shortKey)
                .orElseThrow(() -> new RuntimeException("URL not found for key: " + shortKey));

        return mapping.getOriginalUrl();
    }
}
