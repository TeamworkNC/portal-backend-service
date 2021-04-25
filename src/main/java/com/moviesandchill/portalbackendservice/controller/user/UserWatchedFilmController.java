package com.moviesandchill.portalbackendservice.controller.user;

import com.moviesandchill.portalbackendservice.dto.film.film.FilmDto;
import com.moviesandchill.portalbackendservice.service.user.UserWatchedFilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users/{userId}/wanted-films",
        produces = "application/json"
)
@Slf4j
public class UserWatchedFilmController {
    private final UserWatchedFilmService userWatchedFilmService;

    public UserWatchedFilmController(UserWatchedFilmService userWatchedFilmService) {
        this.userWatchedFilmService = userWatchedFilmService;
    }

    @GetMapping
    public List<FilmDto> getAllWatchedFilms(@PathVariable long userId) {
        return userWatchedFilmService.getAllWatchedFilms(userId);
    }

    @PostMapping
    public void addWatchedFilm(@PathVariable long userId, @RequestBody long filmId) {
        userWatchedFilmService.addWatchedFilm(userId, filmId);
    }

    @DeleteMapping
    public void deleteAllWatchedFilms(@PathVariable long userId) {
        userWatchedFilmService.deleteAllWatchedFilms(userId);
    }

    @DeleteMapping("/{filmId}")
    public void deleteWatchedFilm(@PathVariable long userId, @PathVariable long filmId) {
        userWatchedFilmService.deleteWatchedFilm(userId, filmId);
    }
}
