package com.moviesandchill.portalbackendservice.controller;

import com.moviesandchill.portalbackendservice.dto.film.film.FilmDto;
import com.moviesandchill.portalbackendservice.dto.film.genre.GenreDto;
import com.moviesandchill.portalbackendservice.dto.film.review.ReviewDto;
import com.moviesandchill.portalbackendservice.dto.film.screenshot.ScreenshotDto;
import com.moviesandchill.portalbackendservice.dto.film.staff.StaffDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.service.film.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/films",
        produces = "application/json"
)
public class FilmController {

    private final FilmService filmService;
    private final CommonMapper commonMapper;

    public FilmController(FilmService filmService, CommonMapper commonMapper) {
        this.filmService = filmService;
        this.commonMapper = commonMapper;
    }

    @GetMapping
    public List<FilmDto> getAllFilm() {
        return filmService.getAllFilm();
    }

    @DeleteMapping
    @Secured("ROLE_ADMIN")
    public void deleteAllFilm() {
        filmService.deleteAllFilm();
    }

    @GetMapping("/{filmId}")
    public ResponseEntity<FilmDto> getFilmById(@PathVariable Long filmId) {
        return commonMapper.toResponseEntity(filmService.getFilmById(filmId));
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<FilmDto> addFilm(@RequestBody FilmDto film_dto) {
        return commonMapper.toResponseEntity(filmService.addFilm(film_dto));
    }

    @DeleteMapping("/{filmId}")
    @Secured("ROLE_ADMIN")
    public void deleteFilmById(@PathVariable Long filmId) {
        commonMapper.toResponseEntity(filmService.getFilmById(filmId));
    }

    @GetMapping("/{filmId}/genres")
    public List<GenreDto> getAllGenreWithFilm(@PathVariable Long filmId) {
        return filmService.getAllGenreWithFilm(filmId);
    }

    @PostMapping("/{filmId}/addGenre/{genreId}")
    @Secured("ROLE_ADMIN")
    public void addGenreToFilm(@PathVariable Long filmId, @PathVariable Long genreId) throws Exception {
        filmService.addGenreToFilm(filmId, genreId);
    }

    @GetMapping("/{filmId}/staffs")
    public List<StaffDto> getAllStaffWithFilm(@PathVariable Long filmId) {
        return filmService.getAllStaffWithFilm(filmId);
    }

    @PostMapping("/{filmId}/addStaff/{staffId}")
    @Secured("ROLE_ADMIN")
    public void addStaffToFilm(@PathVariable Long filmId, @PathVariable Long staffId) throws Exception {
        filmService.addStaffToFilm(filmId, staffId);
    }

    @GetMapping("/{filmId}/reviews")
    public List<ReviewDto> getAllReviewWithFilm(@PathVariable Long filmId) {
        return filmService.getAllReviewWithFilm(filmId);
    }

    @PostMapping("/{filmId}/addReview/{reviewId}")
    @Secured("ROLE_ADMIN")
    public void addReviewToFilm(@PathVariable Long filmId, @PathVariable Long reviewId) throws Exception {
        filmService.addReviewToFilm(filmId, reviewId);
    }

    @GetMapping("/{filmId}/screenshots")
    public List<ScreenshotDto> getAllScreenshotWithFilm(@PathVariable Long filmId) {
        return filmService.getAllScreenshotWithFilm(filmId);
    }

    @PostMapping("/{filmId}/addScreenshot/{screenshotId}")
    @Secured("ROLE_ADMIN")
    public void addScreenshotToFilm(@PathVariable Long filmId, @PathVariable Long screenshotId) throws Exception {
        filmService.addScreenshotToFilm(filmId, screenshotId);
    }
}
