package dev.yuulhkt.shortener_this.domain.UrlShortener;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "urls")
@Table(name = "urls")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UrlShortener {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "shortened_url")
    private String shortenedUrl;

    @Column(name = "created_at")
    private Date createdAt;

}
