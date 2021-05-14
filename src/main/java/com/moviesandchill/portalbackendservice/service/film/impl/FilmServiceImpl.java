package com.moviesandchill.portalbackendservice.service.film.impl;

import com.moviesandchill.portalbackendservice.dto.film.Filter;
import com.moviesandchill.portalbackendservice.dto.film.agelimit.AgeLimitDto;
import com.moviesandchill.portalbackendservice.dto.film.country.CountryDto;
import com.moviesandchill.portalbackendservice.dto.film.film.FilmDto;
import com.moviesandchill.portalbackendservice.dto.film.film.FilmPageDto;
import com.moviesandchill.portalbackendservice.dto.film.film.FullFilmDto;
import com.moviesandchill.portalbackendservice.dto.film.film.RandFilmDto;
import com.moviesandchill.portalbackendservice.dto.film.genre.GenreDto;
import com.moviesandchill.portalbackendservice.dto.film.review.FullReviewDto;
import com.moviesandchill.portalbackendservice.dto.film.review.ReviewDto;
import com.moviesandchill.portalbackendservice.dto.film.screenshot.ScreenshotDto;
import com.moviesandchill.portalbackendservice.dto.film.staff.StaffDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.mapper.FilmMapper;
import com.moviesandchill.portalbackendservice.mapper.ReviewMapper;
import com.moviesandchill.portalbackendservice.service.film.FilmService;
import com.moviesandchill.portalbackendservice.service.user.UserService;
import com.moviesandchill.portalbackendservice.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FilmServiceImpl implements FilmService {

    private String filmServiceUrl;

    private final CommonMapper commonMapper;

    private UserService userService;

    private final FilmMapper filmMapper;

    private final ReviewMapper reviewMapper;

    @Autowired
    public FilmServiceImpl(CommonMapper commonMapper,FilmMapper filmMapper,ReviewMapper reviewMapper) {
        this.commonMapper = commonMapper;
        this.filmMapper = filmMapper;
        this.reviewMapper = reviewMapper;
    }


    @Override
    public List<FilmDto> getAllFilm() {
        String url = filmServiceUrl + "/films";
        Optional<FilmDto[]> listFilmDtoOptional = RestTemplateUtils.get(url, FilmDto[].class);
        return commonMapper.toList(listFilmDtoOptional);
    }

    @Override
    public List<FilmPageDto> searchFilm(String searchString, Filter filter) {
        String url = filmServiceUrl + "/es/search";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("search", searchString);
        FilmPageDto[] listFilmDtoOptional =new RestTemplate().postForObject(builder.build().toUriString(),filter, FilmPageDto[].class);
        return commonMapper.toList(listFilmDtoOptional);
    }

    @Override
    public List<FilmPageDto> getAllPageFilm() {
        String url = filmServiceUrl + "/films/allpage";
        Optional<FilmPageDto[]> listFilmDtoOptional = RestTemplateUtils.get(url, FilmPageDto[].class);
        return commonMapper.toList(listFilmDtoOptional);
    }

    public List<FilmPageDto> getFirstNewFilms() {
        String url = filmServiceUrl + "/films/newfilms";
        Optional<FilmPageDto[]> listFilmDtoOptional = RestTemplateUtils.get(url, FilmPageDto[].class);
        return commonMapper.toList(listFilmDtoOptional);
    }

    public List<RandFilmDto> getRandomThreeFilms() {
        String url = filmServiceUrl + "/films/randomthreefilms";
        Optional<RandFilmDto[]> listFilmDtoOptional = RestTemplateUtils.get(url, RandFilmDto[].class);
        return commonMapper.toList(listFilmDtoOptional);
    }

    public List<FilmPageDto> getFirstPopularFilms() {
        String url = filmServiceUrl + "/films/popularfilms";
        Optional<FilmPageDto[]> listFilmDtoOptional = RestTemplateUtils.get(url, FilmPageDto[].class);
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
    public Optional<FullFilmDto> getFullFilmById(Long film_id)  {
        Optional<FilmDto> film = getFilmById(film_id);
        if(film.isPresent()){
            FullFilmDto fullFilmDto = filmMapper.dtoToFullFilm(film.get());

            float rating = getRatingFilmById(film_id);
            fullFilmDto.setRating(rating);

            AgeLimitDto ageLimit = getAgeLimitByFilmId(film_id).orElse(null);
            fullFilmDto.setAgeLimit(ageLimit);

            CountryDto country = getCountryByFilmId(film_id).orElse(null);
            fullFilmDto.setCountry(country);

            List<GenreDto> genres = getAllGenreWithFilm(film_id);
            fullFilmDto.setGenres(genres);

            List<StaffDto> actors = getAllActorsByFilm(film_id);
            fullFilmDto.setActors(actors);

            List<StaffDto> producers = getAllProducersByFilm(film_id);
            fullFilmDto.setProducers(producers);

            List<ReviewDto> reviews = getAllReviewWithFilm(film_id);
            List<FullReviewDto> fullreviews = new ArrayList<>();
            for(ReviewDto reviewDto : reviews){
                if(reviewDto.getIdUser() != null){
                    FullReviewDto fullReviewDto = reviewMapper.mapToFullDto(reviewDto);
                    fullReviewDto.setUser(userService.getUser(reviewDto.getIdUser()));
                    fullreviews.add(fullReviewDto);
                }
            }
            fullFilmDto.setReviews(fullreviews);

            List<ScreenshotDto> screenshots = getAllScreenshotWithFilm(film_id);
            fullFilmDto.setScreenshots(screenshots);

            return Optional.of(fullFilmDto);
        }
        return null;
    }



    @Override
    public float getRatingFilmById(Long film_id) {
        String url = filmServiceUrl + "/films/ratingFilm/" + film_id;
        return RestTemplateUtils.get(url, null, Float.class).orElse(0f);
    }

    @Override
    public List<StaffDto> getAllActorsByFilm(Long film_id) {
        String url = filmServiceUrl + "/films/" + film_id + "/actors";
        Optional<StaffDto[]> listActorsOptional = RestTemplateUtils.get(url, StaffDto[].class);
        return commonMapper.toList(listActorsOptional);
    }

    @Override
    public List<StaffDto> getAllProducersByFilm(Long film_id) {
        String url = filmServiceUrl + "/films/" + film_id + "/producers";
        Optional<StaffDto[]> listProducersOptional = RestTemplateUtils.get(url, StaffDto[].class);
        return commonMapper.toList(listProducersOptional);
    }

    @Override
    public Optional<AgeLimitDto> getAgeLimitByFilmId(Long film_id) {
        String url = filmServiceUrl + "/films/" + film_id + "/ageLimit";
        return RestTemplateUtils.get(url, null, AgeLimitDto.class);
    }

    @Override
    public void setAgeLimitByFilmId(Long film_id, Long ageLimitID) throws Exception {
        String url = filmServiceUrl + "/films/" + film_id + "/setAgeLimit/" + ageLimitID;
        RestTemplateUtils.post(url, null);
    }

    @Override
    public Optional<CountryDto> getCountryByFilmId(Long film_id) {
        String url = filmServiceUrl + "/films/" + film_id + "/country";
        return RestTemplateUtils.get(url, null, CountryDto.class);
    }

    @Override
    public void setCountryByFilmId(Long film_id, Long countryID) throws Exception {
        String url = filmServiceUrl + "/films/" + film_id + "/setCountry/" + countryID;
        RestTemplateUtils.post(url, null);
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
    public void setFilmServiceUrl(@Value("${endpoint.film-service.url}") String filmServiceUrl) {
        this.filmServiceUrl = filmServiceUrl;
    }

    @Autowired
    @Lazy
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
