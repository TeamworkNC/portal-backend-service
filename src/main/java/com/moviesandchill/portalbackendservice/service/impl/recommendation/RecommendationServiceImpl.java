package com.moviesandchill.portalbackendservice.service.impl.recommendation;

import com.moviesandchill.portalbackendservice.dto.film.film.FilmDto;
import com.moviesandchill.portalbackendservice.service.film.FilmService;
import com.moviesandchill.portalbackendservice.service.recommendation.RecommendationService;
import com.moviesandchill.portalbackendservice.utils.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    private final FilmService filmService;
    @Value("${endpoint.recommendation-service.url}")
    private String baseUrl;

    public RecommendationServiceImpl(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public List<FilmDto> recommend(long userId) {
        String url = baseUrl + "/api/v1/recommendations/films?userId=" + userId;
        Long[] filmIds = RestTemplateUtils.get(url, Long[].class).orElseThrow();

        List<FilmDto> films = new ArrayList<>();
        for (var filmId : filmIds) {
            FilmDto film = filmService.getFilmById(filmId).orElseThrow();
            films.add(film);
        }
        return films;
    }
}
