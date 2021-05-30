package com.moviesandchill.portalbackendservice.service.film.impl;

import com.moviesandchill.portalbackendservice.dto.film.review.NewReviewDto;
import com.moviesandchill.portalbackendservice.dto.film.review.ReviewDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.mapper.ReviewMapper;
import com.moviesandchill.portalbackendservice.service.film.ReviewService;
import com.moviesandchill.portalbackendservice.utils.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private String filmServiceUrl;

    private final CommonMapper commonMapper;

    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(CommonMapper commonMapper, ReviewMapper reviewMapper) {
        this.commonMapper = commonMapper;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDto> getAllReview() {
        String url = filmServiceUrl + "/reviews";
        Optional<ReviewDto[]> listReviewDtoOptional = RestTemplateUtils.get(url, ReviewDto[].class);
        return commonMapper.toList(listReviewDtoOptional);
    }

    @Override
    public void deleteAllReview() {
        String url = filmServiceUrl + "/reviews";
        RestTemplateUtils.delete(url);
    }

    @Override
    public Optional<ReviewDto> getReviewById(Long reviewId) {
        String url = filmServiceUrl + "/reviews/" + reviewId;
        return RestTemplateUtils.get(url, null, ReviewDto.class);
    }

    @Override
    public Optional<ReviewDto> addReview(NewReviewDto reviewDto, Long filmId) {
        String url = filmServiceUrl + "/reviews";
        Optional<ReviewDto> review = RestTemplateUtils.post(url, reviewMapper.mapNewToDto(reviewDto), ReviewDto.class);
        if (review.isPresent()) {
            String urlAddReview = filmServiceUrl + "/films/" + filmId + "/addReview/" + review.get().getIdReview();
            new RestTemplate().exchange(urlAddReview, HttpMethod.POST, null, void.class);
        }
        return review;
    }

    @Override
    public void deleteReviewById(Long reviewId) {
        String url = filmServiceUrl + "/reviews/" + reviewId;
        RestTemplateUtils.delete(url);
    }

    @Autowired
    public void setFilmServiceUrl(@Value("${endpoints.film-service-url}") String filmServiceUrl) {
        this.filmServiceUrl = filmServiceUrl;
    }
}
