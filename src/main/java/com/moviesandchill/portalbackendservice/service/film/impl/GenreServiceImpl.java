package com.moviesandchill.portalbackendservice.service.film.impl;

import com.moviesandchill.portalbackendservice.dto.film.genre.GenreDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.service.film.GenreService;
import com.moviesandchill.portalbackendservice.utils.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private String filmServiceUrl;

    private final CommonMapper commonMapper;

    public GenreServiceImpl(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }

    @Override
    public List<GenreDto> getAllGenre() {
        String url = filmServiceUrl + "/genres";
        Optional<GenreDto[]> listGenreDtoOptional = RestTemplateUtils.get(url, GenreDto[].class);
        return commonMapper.toList(listGenreDtoOptional);
    }

    @Override
    public void deleteAllGenre() {
        String url = filmServiceUrl + "/genres";
        RestTemplateUtils.delete(url);
    }

    @Override
    public Optional<GenreDto> getGenreById(Long genreId) {
        String url = filmServiceUrl + "/genres/" + genreId;
        return RestTemplateUtils.get(url, null, GenreDto.class);
    }

    @Override
    public Optional<GenreDto> addGenre(GenreDto genreDto) {
        String url = filmServiceUrl + "/genres";
        return RestTemplateUtils.post(url, genreDto, GenreDto.class);
    }

    @Override
    public void deleteGenreById(Long genreId) {
        String url = filmServiceUrl + "/genres/" + genreId;
        RestTemplateUtils.delete(url);
    }

    @Autowired
    public void setFilmServiceUrl(@Value("${endpoints.film-service-url}") String filmServiceUrl) {
        this.filmServiceUrl = filmServiceUrl;
    }
}
