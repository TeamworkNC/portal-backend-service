package com.moviesandchill.portalbackendservice.service.user;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.moviesandchill.portalbackendservice.dto.film.film.FilmDto;
import com.moviesandchill.portalbackendservice.service.film.FilmService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserWatchedFilmService {

    private final String userServiceUrl;
    private final FilmService filmService;
    private final RestTemplate restTemplate;

    public UserWatchedFilmService(@Value("${endpoint.user-service.url}") String userServiceUrl, FilmService filmService, RestTemplate restTemplate) {
        this.userServiceUrl = userServiceUrl;
        this.filmService = filmService;
        this.restTemplate = restTemplate;
    }

    public List<FilmDto> getAllWatchedFilms(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/wanted-films";
        var json = restTemplate.getForObject(url, ArrayNode.class);
        List<FilmDto> dtos = new ArrayList<>();

        assert json != null;
        for (var obj : json) {
            long filmId = obj.get("filmId").asLong();
            var filmDto = filmService.getFilmById(filmId)
                    .orElseThrow();
            dtos.add(filmDto);
        }
        return dtos;
    }

    public void addWatchedFilm(long userId, long filmId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/wanted-films";
        restTemplate.postForObject(url, filmId, Void.class);
    }

    public void deleteAllWatchedFilms(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/wanted-films";
        restTemplate.delete(url);
    }

    public void deleteWatchedFilm(long userId, long filmId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/wanted-films/" + filmId;
        restTemplate.delete(url);
    }
}
