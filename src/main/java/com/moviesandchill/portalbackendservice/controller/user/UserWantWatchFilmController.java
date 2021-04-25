package com.moviesandchill.portalbackendservice.controller.user;

import com.moviesandchill.portalbackendservice.dto.film.film.FilmDto;
import com.moviesandchill.portalbackendservice.service.user.UserWantWatchFilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users/{userId}/want-watch-films",
        produces = "application/json"
)
@Slf4j
public class UserWantWatchFilmController {
    private final UserWantWatchFilmService userWantWatchFilmService;

    public UserWantWatchFilmController(UserWantWatchFilmService userWantWatchFilmService) {
        this.userWantWatchFilmService = userWantWatchFilmService;
    }

    @GetMapping
    public List<FilmDto> getAllWantWatchFilms(@PathVariable long userId) {
        return userWantWatchFilmService.getAllWantWatchFilms(userId);
    }

    @PostMapping
    public void addWantWatchFilm(@PathVariable long userId, @RequestBody long filmId) {
        userWantWatchFilmService.addWantWatchFilm(userId, filmId);
    }

    @DeleteMapping
    public void deleteAllWantWatchFilms(@PathVariable long userId) {
        userWantWatchFilmService.deleteAllWantWatchFilms(userId);
    }

    @DeleteMapping("/{filmId}")
    public void deleteWantWatchFilm(@PathVariable long userId, @PathVariable long filmId) {
        userWantWatchFilmService.deleteWantWatchFilm(userId, filmId);
    }
}
