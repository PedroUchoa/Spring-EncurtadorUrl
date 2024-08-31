package com.pedro.encurtador_url.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String origalUrl;

    private String newUrl;

    private LocalDateTime expirationDate;

    public Url(String origalUrl, String newUrl, LocalDateTime expirationDate) {
        this.origalUrl = origalUrl;
        this.newUrl = newUrl;
        this.expirationDate = expirationDate;
    }
}
