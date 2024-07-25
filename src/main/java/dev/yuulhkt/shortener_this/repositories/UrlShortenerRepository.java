package dev.yuulhkt.shortener_this.repositories;

import dev.yuulhkt.shortener_this.domain.UrlShortener.UrlShortener;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlShortenerRepository extends JpaRepository<UrlShortener, String> {

    UrlShortener findByShortenedUrl(String shortenedUrl);
}
