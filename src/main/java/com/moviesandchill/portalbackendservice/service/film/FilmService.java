package com.moviesandchill.portalbackendservice.service.film;

import com.moviesandchill.portalbackendservice.dto.film.Filter;
import com.moviesandchill.portalbackendservice.dto.film.agelimit.AgeLimitDto;
import com.moviesandchill.portalbackendservice.dto.film.country.CountryDto;
import com.moviesandchill.portalbackendservice.dto.film.film.FilmDto;
import com.moviesandchill.portalbackendservice.dto.film.film.FilmPageDto;
import com.moviesandchill.portalbackendservice.dto.film.film.FullFilmDto;
import com.moviesandchill.portalbackendservice.dto.film.film.RandFilmDto;
import com.moviesandchill.portalbackendservice.dto.film.genre.GenreDto;
import com.moviesandchill.portalbackendservice.dto.film.review.ReviewDto;
import com.moviesandchill.portalbackendservice.dto.film.screenshot.ScreenshotDto;
import com.moviesandchill.portalbackendservice.dto.film.staff.StaffDto;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface FilmService {

    List<FilmDto> getAllFilm();

    List<FilmPageDto> searchFilm(String searchString, Filter filter);

    public List<FilmPageDto> getAllPageFilm();

    public List<FilmPageDto> getFirstNewFilms();

    public List<RandFilmDto> getRandomThreeFilms();

    public List<FilmPageDto> getFirstPopularFilms();

    void deleteAllFilm();

    Optional<FilmDto> getFilmById(Long film_id);

    FilmPageDto getFilmPageDtoById(Long film_id);

    Optional<FullFilmDto> getFullFilmById(Long film_id);

    float getRatingFilmById(Long film_id);

    List<StaffDto> getAllActorsByFilm(Long film_id) ;

    List<StaffDto> getAllProducersByFilm(Long film_id) ;

    Optional<AgeLimitDto> getAgeLimitByFilmId(Long film_id);

    void setAgeLimitByFilmId(Long film_id,Long ageLimitID) throws Exception;

    Optional<CountryDto> getCountryByFilmId(Long film_id);

    void setCountryByFilmId(Long film_id,Long countryID) throws Exception;

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
