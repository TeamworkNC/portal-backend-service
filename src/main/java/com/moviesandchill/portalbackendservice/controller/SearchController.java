package com.moviesandchill.portalbackendservice.controller;

import com.moviesandchill.portalbackendservice.dto.film.Filter;
import com.moviesandchill.portalbackendservice.dto.film.film.FilmPageDto;
import com.moviesandchill.portalbackendservice.dto.user.user.UserDto;
import com.moviesandchill.portalbackendservice.service.film.FilmService;
import com.moviesandchill.portalbackendservice.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/se",
        produces = "application/json"
)
public class SearchController {

    private final FilmService filmService;
    private final UserService userService;

    public SearchController(FilmService filmService, UserService userService) {
        this.filmService = filmService;
        this.userService = userService;
    }

    @PostMapping("/searchFilm")
    public List<FilmPageDto> search(@RequestParam("search") String searchString, @RequestBody Filter filter) throws IOException {
        return filmService.searchFilm(searchString,filter);
    }

    @PostMapping("/searchUser")
    public List<UserDto> search(@RequestParam("search") String searchString) throws IOException {
        return userService.search(searchString);
    }
}
