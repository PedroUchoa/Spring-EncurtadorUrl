package com.pedro.encurtador_url.repositorys;

import com.pedro.encurtador_url.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface UrlRepository extends JpaRepository<Url,String> {
    Url findByNewUrl(String newUrl);

    boolean existsUrlByNewUrlAndExpirationDateIsAfter(String newUrl, LocalDateTime date);
}
