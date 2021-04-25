package com.moviesandchill.portalbackendservice.dto.film.film;

import com.moviesandchill.portalbackendservice.dto.film.agelimit.AgeLimitDto;
import com.moviesandchill.portalbackendservice.dto.film.country.CountryDto;
import com.moviesandchill.portalbackendservice.dto.film.genre.GenreDto;
import com.moviesandchill.portalbackendservice.dto.film.review.ReviewDto;
import com.moviesandchill.portalbackendservice.dto.film.screenshot.ScreenshotDto;
import com.moviesandchill.portalbackendservice.dto.film.staff.StaffDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullFilmDto {

    private Long id_film;

    private String film_title;

    private LocalDateTime duration;

    private LocalDate release_date;

    private String film_poster;

    private String film_trailer;

    private String film_video;

    private BigInteger film_budget;

    private String description;

    private float rating;

    private AgeLimitDto ageLimit;

    private CountryDto country;

    private List<GenreDto> genres = new ArrayList<>();

    private List<StaffDto> actors = new ArrayList<>();

    private List<StaffDto> producers = new ArrayList<>();

    private List<ReviewDto> reviews = new ArrayList<ReviewDto>();

    private List<ScreenshotDto> screenshots = new ArrayList<ScreenshotDto>();
}