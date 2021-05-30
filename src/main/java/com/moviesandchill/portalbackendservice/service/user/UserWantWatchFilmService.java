package com.moviesandchill.portalbackendservice.service.user;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.moviesandchill.portalbackendservice.dto.film.film.FilmDto;
import com.moviesandchill.portalbackendservice.service.film.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserWantWatchFilmService {

    private final String userServiceUrl;
    private FilmService filmService;
    private final RestTemplate restTemplate;

    public UserWantWatchFilmService(@Value("${endpoints.user-service-url}") String userServiceUrl, RestTemplate restTemplate) {
        this.userServiceUrl = userServiceUrl;
        this.restTemplate = restTemplate;
    }


    public List<FilmDto> getAllWantWatchFilms(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/want-watch-films";
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

    public void addWantWatchFilm(long userId, long filmId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/want-watch-films";
        restTemplate.postForObject(url, filmId, Void.class);
    }

    public void deleteAllWantWatchFilms(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/want-watch-films";
        restTemplate.delete(url);
    }

    public void deleteWantWatchFilm(long userId, long filmId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/want-watch-films/" + filmId;
        restTemplate.delete(url);
    }

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }
}
