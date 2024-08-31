package com.pedro.encurtador_url.services;

import com.pedro.encurtador_url.dtos.UrlDto;
import com.pedro.encurtador_url.exceptions.DateExpiredExeception;
import com.pedro.encurtador_url.models.Url;
import com.pedro.encurtador_url.repositorys.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

@Service
public class UrlServices {

    @Autowired
    private UrlRepository urlRepository;

    public UrlDto newUrl(UrlDto originalUrl, HttpServletRequest servletRequest){
        String urlRandom;
        LocalDateTime date = LocalDateTime.now();
        do{
            urlRandom = RandomStringUtils.randomAlphanumeric(5,10);
        }while (urlRepository.existsUrlByNewUrlAndExpirationDateIsAfter(urlRandom, date));
        Url url = new Url(originalUrl.url(),urlRandom, LocalDateTime.now().plusMinutes(1));
        urlRepository.save(url);
        String newUrl =  servletRequest.getRequestURL().toString().replace("shorten-url",urlRandom);
        return new UrlDto(newUrl);
    }

    public HttpHeaders descodeUrl(String newUrl){
        Url url = urlRepository.findByNewUrl(newUrl);
        if(LocalDateTime.now().isAfter(url.getExpirationDate())) throw new DateExpiredExeception();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation((URI.create(url.getOrigalUrl())));
        return headers;

    }

}
