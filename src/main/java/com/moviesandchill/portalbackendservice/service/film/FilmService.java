package com.moviesandchill.portalbackendservice.service.film;

import com.moviesandchill.portalbackendservice.dto.film.film.FilmDto;
import com.moviesandchill.portalbackendservice.dto.film.genre.GenreDto;
import com.moviesandchill.portalbackendservice.dto.film.review.ReviewDto;
import com.moviesandchill.portalbackendservice.dto.film.screenshot.ScreenshotDto;
import com.moviesandchill.portalbackendservice.dto.film.staff.StaffDto;

import java.util.List;
import java.util.Optional;

public interface FilmService {

    List<FilmDto> getAllFilm();

    void deleteAllFilm();

    Optional<FilmDto> getFilmById(Long film_id);

    Optional<FilmDto> addFilm(FilmDto film_dto);

    void deleteFilmById(Long film_id);

    List<GenreDto> getAllGenreWithFilm(Long film_id);

    void addGenreToFilm(Long film_id,Long genre_id) throws Exception;

    List<StaffDto> getAllStaffWithFilm(Long film_id);

    void addStaffToFilm(Long film_id,Long staff_id) throws Exception;

    List<ReviewDto> getAllReviewWithFilm(Long film_id);

    void addReviewToFilm(Long film_id,Long review_id) throws Exception;

    List<ScreenshotDto> getAllScreenshotWithFilm(Long film_id);

    void addScreenshotToFilm(Long film_id,Long screenshot_id) throws Exception;
}
