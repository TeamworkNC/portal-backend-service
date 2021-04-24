package com.moviesandchill.portalbackendservice.controller.recommendation;

import com.moviesandchill.portalbackendservice.dto.film.film.FilmDto;
import com.moviesandchill.portalbackendservice.service.recommendation.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/recommendations",
        produces = "application/json"
)
@Slf4j
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/film")
    public List<FilmDto> recommend(@RequestParam long userId) {
        return recommendationService.recommend(userId);
    }
}
