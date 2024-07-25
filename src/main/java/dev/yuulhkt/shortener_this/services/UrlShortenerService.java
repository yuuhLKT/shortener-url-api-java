package dev.yuulhkt.shortener_this.services;

import dev.yuulhkt.shortener_this.domain.UrlShortener.RequestUrl;
import dev.yuulhkt.shortener_this.domain.UrlShortener.UrlShortener;
import dev.yuulhkt.shortener_this.repositories.UrlShortenerRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UrlShortenerService {

    private final UrlShortenerRepository urlShortenerRepository;

    public UrlShortenerService(UrlShortenerRepository urlShortenerRepository) {
        this.urlShortenerRepository = urlShortenerRepository;
    }

    private String generateShortenedUrl() {
        return RandomStringUtils.randomAlphanumeric(6);
    }

    public UrlShortener createShortenedUrl(RequestUrl requestUrl) {
        UrlShortener urlShortener = new UrlShortener();
        urlShortener.setOriginalUrl(requestUrl.originalUrl());
        urlShortener.setShortenedUrl(generateShortenedUrl());
        urlShortener.setCreatedAt(new Date());
        return urlShortenerRepository.save(urlShortener);
    }

    public UrlShortener getOriginalUrl(String shortenedUrl) {
        return urlShortenerRepository.findByShortenedUrl(shortenedUrl);
    }

    public List<UrlShortener> getAllUrls() {
        return urlShortenerRepository.findAll();
    }

    public void deleteById(String id) {
        urlShortenerRepository.deleteById(id);
    }

}
