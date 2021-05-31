package com.moviesandchill.portalbackendservice.service.recommendation;

import com.moviesandchill.portalbackendservice.dto.film.film.FullFilmDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class RecommendationService {

    private final RestTemplate restTemplate;
    @Value("${endpoints.recommendation-service-url}")
    private String baseUrl;

    public RecommendationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<FullFilmDto> recommend(long userId) {
        String url = baseUrl + "/api/v1/users/" + userId + "/recommendations";
        FullFilmDto[] filmDtos = restTemplate.getForObject(url, FullFilmDto[].class);
        return Arrays.asList(Objects.requireNonNull(filmDtos));
    }
}
