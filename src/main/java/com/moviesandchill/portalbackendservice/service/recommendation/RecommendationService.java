package com.moviesandchill.portalbackendservice.service.recommendation;

import com.moviesandchill.portalbackendservice.dto.film.film.FilmDto;

import java.util.List;

public interface RecommendationService {
    List<FilmDto> recommend(long userId);
}
