package com.moviesandchill.portalbackendservice.service.user;

import com.moviesandchill.portalbackendservice.dto.film.staff.StaffDto;
import com.moviesandchill.portalbackendservice.service.film.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class UserFavoriteStaffService {
    private final String userServiceUrl;
    private final FilmService filmService;
    private final RestTemplate restTemplate;

    public UserFavoriteStaffService(@Value("${endpoint.user-service.url}") String userServiceUrl, FilmService filmService, RestTemplate restTemplate) {
        this.userServiceUrl = userServiceUrl;
        this.filmService = filmService;
        this.restTemplate = restTemplate;
    }

    public List<StaffDto> getAllFavoriteStaffs(long userId) {
        //FIXME
//        String url = userServiceUrl + "/api/v1/favorite-staffs";
//        var json = restTemplate.getForObject(url, ArrayNode.class);
//        List<FilmDto> dtos = new ArrayList<>();
//
//        assert json != null;
//        for (var obj : json) {
//            long filmId = obj.get("filmId").asLong();
//            var filmDto = filmService.staff(filmId)
//                    .orElseThrow();
//            dtos.add(filmDto);
//        }
        return List.of();
    }

    public void addFavoriteStaff(long userId, long staffId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/favorite-staffs";
        restTemplate.postForObject(url, staffId, Void.class);
    }

    public void deleteAllFavoriteStaffs(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/favorite-staffs";
        restTemplate.delete(url);
    }

    public void deleteFavoriteStaff(long userId, long staffId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/favorite-staffs/" + staffId;
        restTemplate.delete(url);
    }
}
