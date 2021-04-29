package com.moviesandchill.portalbackendservice.service.film;

import com.moviesandchill.portalbackendservice.dto.film.genre.GenreDto;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    List<GenreDto> getAllGenre();

    void deleteAllGenre();

    Optional<GenreDto> getGenreById(Long genreId);

    Optional<GenreDto> addGenre(GenreDto genreDto);

    void deleteGenreById(Long genreId);
}
