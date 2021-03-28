package com.moviesandchill.portalbackendservice.utils;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public final class RestTemplateUtils {

    private RestTemplateUtils() {
    }

    private static <T, U> Optional<U> exchange(String url, HttpMethod method, T body, Class<U> responseType) {
        RestTemplate restTemplate = createRestTemplate();

        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

        RequestEntity<T> entity = RequestEntity.method(method, url)
                .body(body);
        try {
            ResponseEntity<U> responseEntity = restTemplate.exchange(entity, responseType);
            return Optional.ofNullable(responseEntity.getBody());
        } catch (HttpServerErrorException e) {
            return Optional.empty();
        }
    }

    private static <T, U> Optional<U> exchange(String url, HttpMethod method, Class<U> responseType) {
        RestTemplate restTemplate = createRestTemplate();

        RequestEntity<Void> entity = RequestEntity.method(method, url).build();

        try {
            ResponseEntity<U> responseEntity = restTemplate.exchange(entity, responseType);
            return Optional.ofNullable(responseEntity.getBody());
        } catch (HttpServerErrorException e) {
            return Optional.empty();
        }
    }

    private static RestTemplate createRestTemplate() {
        return new RestTemplateBuilder()
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    public static <T, U> Optional<U> get(String url, T body, Class<U> responseType) {
        return exchange(url, HttpMethod.GET, body, responseType);
    }

    public static <T, U> Optional<U> post(String url, T body, Class<U> responseType) {
        return exchange(url, HttpMethod.POST, body, responseType);
    }

    public static <T, U> Optional<U> put(String url, T body, Class<U> responseType) {
        return exchange(url, HttpMethod.PUT, body, responseType);
    }

    public static <T, U> Optional<U> delete(String url, T body, Class<U> responseType) {
        return exchange(url, HttpMethod.DELETE, body, responseType);
    }

    public static <T, U> Optional<U> get(String url, Class<U> responseType) {
        return exchange(url, HttpMethod.GET, responseType);
    }

    public static <T, U> Optional<U> post(String url, Class<U> responseType) {
        return exchange(url, HttpMethod.POST, responseType);
    }

    public static <T, U> Optional<U> put(String url, Class<U> responseType) {
        return exchange(url, HttpMethod.PUT, responseType);
    }

    public static <T, U> Optional<U> delete(String url, Class<U> responseType) {
        return exchange(url, HttpMethod.DELETE, responseType);
    }

    public static <T> boolean get(String url, T body) {
        return get(url, body, Void.class).isPresent();
    }

    public static <T> boolean post(String url, T body) {
        return post(url, body, Void.class).isPresent();
    }

    public static <T> boolean put(String url, T body) {
        return put(url, body, Void.class).isPresent();
    }

    public static <T> boolean delete(String url, T body) {
        return delete(url, body, Void.class).isPresent();
    }

    public static <T> boolean get(String url) {
        return get(url, Void.class).isPresent();
    }

    public static <T> boolean delete(String url) {
        return delete(url, Void.class).isPresent();
    }
}

