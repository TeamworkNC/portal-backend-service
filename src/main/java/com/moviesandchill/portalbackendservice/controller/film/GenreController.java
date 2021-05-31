package com.moviesandchill.portalbackendservice.controller.film;

import com.moviesandchill.portalbackendservice.dto.film.genre.GenreDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.service.film.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/genres",
        produces = "application/json"
)
public class GenreController {

    private final GenreService genreService;
    private final CommonMapper commonMapper;

    public GenreController(GenreService genreService, CommonMapper commonMapper) {
        this.genreService = genreService;
        this.commonMapper = commonMapper;
    }

    @GetMapping
    public List<GenreDto> getAllGenre() {
        return genreService.getAllGenre();
    }

    @DeleteMapping
    @Secured("ROLE_USER")
    public void deleteAllGenre() {
        genreService.deleteAllGenre();
    }

    @GetMapping("/{genreId}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable Long genreId) {
        return commonMapper.toResponseEntity(genreService.getGenreById(genreId));
    }

    @PostMapping
    @Secured("ROLE_USER")
    public ResponseEntity<GenreDto> addGenre(@RequestBody GenreDto genreDto) {
        return commonMapper.toResponseEntity(genreService.addGenre(genreDto));
    }

    @DeleteMapping("/{genreId}")
    @Secured("ROLE_USER")
    public void deleteGenreById(@PathVariable Long genreId) {
        genreService.deleteGenreById(genreId);
    }

}
