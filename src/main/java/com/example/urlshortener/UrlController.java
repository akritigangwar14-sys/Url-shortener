package com.example.urlshortener;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    // Endpoint to shorten a URL: GET /shorten?originalUrl=...
    @GetMapping("/shorten")
    public String shortenUrl(@RequestParam String originalUrl) {
        String shortKey = urlService.shortenUrl(originalUrl);
        return "Shortened URL key: " + shortKey;
    }


    // Endpoint to redirect to the original URL: GET /{shortKey}
    // Endpoint to redirect to the original URL: GET /{shortKey}
    @GetMapping("/{shortKey}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortKey) {
        // Ignore static files or anything with a file extension like .html


        String originalUrl = urlService.getOriginalUrl(shortKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
    }


