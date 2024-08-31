package com.pedro.encurtador_url.dtos;

public record UrlDto(String url) {

    public UrlDto(String url) {
        this.url = url;
    }
}
