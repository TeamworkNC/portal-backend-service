package com.moviesandchill.portalbackendservice.service.impl.film;

import com.moviesandchill.portalbackendservice.dto.film.film.FilmDto;
import com.moviesandchill.portalbackendservice.dto.film.genre.GenreDto;
import com.moviesandchill.portalbackendservice.dto.film.review.ReviewDto;
import com.moviesandchill.portalbackendservice.dto.film.screenshot.ScreenshotDto;
import com.moviesandchill.portalbackendservice.dto.film.staff.StaffDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.service.film.FilmService;
import com.moviesandchill.portalbackendservice.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FilmServiceImpl implements FilmService {

    private String filmServiceUrl;

    private final CommonMapper commonMapper;

    @Autowired
    public FilmServiceImpl(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }


    @Override
    public List<FilmDto> getAllFilm() {
        String url = filmServiceUrl + "/films";
        Optional<FilmDto[]> listFilmDtoOptional = RestTemplateUtils.get(url, FilmDto[].class);
        return commonMapper.toList(listFilmDtoOptional);
    }

    @Override
    public void deleteAllFilm() {
        String url = filmServiceUrl + "/films";
        RestTemplateUtils.delete(url);
    }

    @Override
    public Optional<FilmDto> getFilmById(Long film_id) {
        String url = filmServiceUrl + "/films/" + film_id;
        return RestTemplateUtils.get(url, null, FilmDto.class);
    }

    @Override
    public Optional<FilmDto> addFilm(FilmDto film_dto) {
        String url = filmServiceUrl + "/films";
        return RestTemplateUtils.post(url, film_dto, FilmDto.class);
    }

    @Override
    public void deleteFilmById(Long film_id) {
        String url = filmServiceUrl + "/films/" + film_id;
        RestTemplateUtils.delete(url);
    }

    @Override
    public List<GenreDto> getAllGenreWithFilm(Long film_id) {
        String url = filmServiceUrl + "/films/" + film_id + "/genres";
        Optional<GenreDto[]> listGenreDtoOptional = RestTemplateUtils.get(url, GenreDto[].class);
        return commonMapper.toList(listGenreDtoOptional);
    }

    @Override
    public void addGenreToFilm(Long film_id, Long genre_id) throws Exception {
        String url = filmServiceUrl + "/films/" + film_id + "/addGenre/" + genre_id;
        RestTemplateUtils.post(url, null);
    }

    @Override
    public List<StaffDto> getAllStaffWithFilm(Long film_id) {
        String url = filmServiceUrl + "/films/" + film_id + "/staffs";
        Optional<StaffDto[]> listStaffDtoOptional = RestTemplateUtils.get(url, StaffDto[].class);
        return commonMapper.toList(listStaffDtoOptional);
    }

    @Override
    public void addStaffToFilm(Long film_id, Long staff_id) throws Exception {
        String url = filmServiceUrl + "/films/" + film_id + "/addStaff/" + staff_id;
        RestTemplateUtils.post(url, null);
    }

    @Override
    public List<ReviewDto> getAllReviewWithFilm(Long film_id) {
        String url = filmServiceUrl + "/films/" + film_id + "/reviews";
        Optional<ReviewDto[]> listReviewDtoOptional = RestTemplateUtils.get(url, ReviewDto[].class);
        return commonMapper.toList(listReviewDtoOptional);
    }

    @Override
    public void addReviewToFilm(Long film_id, Long review_id) throws Exception {
        String url = filmServiceUrl + "/films/" + film_id + "/addReview/" + review_id;
        RestTemplateUtils.post(url, null);
    }

    @Override
    public List<ScreenshotDto> getAllScreenshotWithFilm(Long film_id) {
        String url = filmServiceUrl + "/films/" + film_id + "/screenshots";
        Optional<ScreenshotDto[]> listScreenshotDtoOptional = RestTemplateUtils.get(url, ScreenshotDto[].class);
        return commonMapper.toList(listScreenshotDtoOptional);
    }

    @Override
    public void addScreenshotToFilm(Long film_id, Long screenshot_id) throws Exception {
        String url = filmServiceUrl + "/films/" + film_id + "/addScreenshot/" + screenshot_id;
        RestTemplateUtils.post(url, null);
    }

    @Autowired
    public void setUserServiceUrl(@Value("${endpoint.film-service.url}") String filmServiceUrl) {
        this.filmServiceUrl = filmServiceUrl;
    }
}
