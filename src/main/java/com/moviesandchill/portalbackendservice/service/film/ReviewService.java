package com.moviesandchill.portalbackendservice.service.film;

import com.moviesandchill.portalbackendservice.dto.film.review.NewReviewDto;
import com.moviesandchill.portalbackendservice.dto.film.review.ReviewDto;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<ReviewDto> getAllReview();

    void deleteAllReview();

    Optional<ReviewDto> getReviewById(Long reviewId);

    Optional<ReviewDto> addReview(NewReviewDto reviewDto, Long filmId);

    void deleteReviewById(Long reviewId);
}
