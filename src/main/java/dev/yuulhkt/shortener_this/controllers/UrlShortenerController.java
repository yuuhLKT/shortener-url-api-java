package dev.yuulhkt.shortener_this.controllers;

import dev.yuulhkt.shortener_this.domain.UrlShortener.RequestUrl;
import dev.yuulhkt.shortener_this.domain.UrlShortener.ResponseUrl;
import dev.yuulhkt.shortener_this.domain.UrlShortener.UrlShortener;
import dev.yuulhkt.shortener_this.services.UrlShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ResponseUrl> shortenUrl(@RequestBody @Valid RequestUrl requestUrl) {
        UrlShortener urlShortener = urlShortenerService.createShortenedUrl(requestUrl);
        String shortenedUrl = "http://localhost:3001/r/" + urlShortener.getShortenedUrl();
        ResponseUrl responseUrl = new ResponseUrl(urlShortener.getId(), shortenedUrl);
        return ResponseEntity.ok(responseUrl);
    }

    @GetMapping("/r/{shortenedUrl}")
    public void redirectUrl(@PathVariable String shortenedUrl, HttpServletResponse response) throws IOException {
        UrlShortener urlShortener = urlShortenerService.getOriginalUrl(shortenedUrl);
        if (urlShortener != null) {
            response.sendRedirect(urlShortener.getOriginalUrl());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No URL mapping found for: " + shortenedUrl);
        }
    }

    @GetMapping("/shorten")
    public ResponseEntity<List<UrlShortener>> getAllUrls() {
        List<UrlShortener> urls = urlShortenerService.getAllUrls();
        return ResponseEntity.ok(urls);
    }

    @DeleteMapping("/shorten/{id}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String id) {
        urlShortenerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}