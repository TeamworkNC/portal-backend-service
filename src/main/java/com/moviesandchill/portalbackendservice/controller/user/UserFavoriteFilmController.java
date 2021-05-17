package com.moviesandchill.portalbackendservice.controller.user;

import com.moviesandchill.portalbackendservice.dto.film.film.FilmDto;
import com.moviesandchill.portalbackendservice.dto.film.film.FilmPageDto;
import com.moviesandchill.portalbackendservice.service.user.UserFavoriteFilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users/{userId}/favorite-films",
        produces = "application/json"
)
@Slf4j
public class UserFavoriteFilmController {

    private final UserFavoriteFilmService userFavoriteFilmService;


    public UserFavoriteFilmController(UserFavoriteFilmService userFavoriteFilmService) {
        this.userFavoriteFilmService = userFavoriteFilmService;
    }

    @GetMapping
    List<FilmPageDto> getAllFavoriteFilms(@PathVariable long userId) {
        return userFavoriteFilmService.getAllFavoriteFilms(userId);
    }

    @PostMapping
    void addFavoriteFilm(@PathVariable long userId, @RequestBody long filmId) {
        userFavoriteFilmService.addFavoriteFilm(userId, filmId);
    }

    @DeleteMapping
    void deleteAllFavoriteFilms(@PathVariable long userId) {
        userFavoriteFilmService.deleteAllFavoriteFilms(userId);
    }

    @DeleteMapping("/{filmId}")
    void deleteFavoriteFilm(@PathVariable long userId, @PathVariable long filmId) {
        userFavoriteFilmService.deleteFavoriteFilm(userId, filmId);
    }
}
