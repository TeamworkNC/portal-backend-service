package com.moviesandchill.portalbackendservice.controller.film;

import com.moviesandchill.portalbackendservice.dto.film.review.NewReviewDto;
import com.moviesandchill.portalbackendservice.dto.film.review.ReviewDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.service.film.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/reviews",
        produces = "application/json"
)
public class ReviewController {

    private final ReviewService reviewService;
    private final CommonMapper commonMapper;

    public ReviewController(ReviewService reviewService, CommonMapper commonMapper) {
        this.reviewService = reviewService;
        this.commonMapper = commonMapper;
    }

    @GetMapping
    public List<ReviewDto> getAllReview() {
        return reviewService.getAllReview();
    }

    @DeleteMapping
    public void deleteAllReview() {
        reviewService.deleteAllReview();
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long reviewId) {
        return commonMapper.toResponseEntity(reviewService.getReviewById(reviewId));
    }

    @PostMapping("/addToFilm/{filmId}")
    public ResponseEntity<ReviewDto> addReview(@RequestBody NewReviewDto newReviewDto,@PathVariable Long filmId) {
        return commonMapper.toResponseEntity(reviewService.addReview(newReviewDto,filmId));
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReviewById(@PathVariable Long reviewId) {
        reviewService.deleteReviewById(reviewId);
    }

}
