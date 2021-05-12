package com.moviesandchill.portalbackendservice.controller;

import com.moviesandchill.portalbackendservice.dto.film.Filter;
import com.moviesandchill.portalbackendservice.dto.film.film.FilmPageDto;
import com.moviesandchill.portalbackendservice.service.film.FilmService;
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

    public SearchController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("/searchFilm")
    public List<FilmPageDto> search(@RequestParam("search") String searchString, @RequestBody Filter filter) throws IOException {
        return filmService.searchFilm(searchString,filter);
    }
}
