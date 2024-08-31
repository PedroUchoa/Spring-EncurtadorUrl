package com.pedro.encurtador_url.controllers;

import com.pedro.encurtador_url.dtos.UrlDto;
import com.pedro.encurtador_url.models.Url;
import com.pedro.encurtador_url.services.UrlServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {

    @Autowired
    private UrlServices urlServices;

    @PostMapping("/shorten-url")
    public ResponseEntity<UrlDto> newUrl(@RequestBody UrlDto originalUrl, HttpServletRequest servletRequest){
        UrlDto url = urlServices.newUrl(originalUrl,servletRequest);
        return  ResponseEntity.ok().body(url);
    }


    @GetMapping("{url}")
    public ResponseEntity<Void> decodeUrl(@PathVariable("url") String url){
        HttpHeaders headers = urlServices.descodeUrl(url);

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

}
