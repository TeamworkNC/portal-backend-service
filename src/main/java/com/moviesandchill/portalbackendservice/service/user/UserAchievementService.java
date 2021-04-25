package com.moviesandchill.portalbackendservice.service.user;

import com.moviesandchill.portalbackendservice.dto.user.achievement.AchievementDto;
import com.moviesandchill.portalbackendservice.dto.user.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserAchievementService {

    private final String userServiceUrl;
    private final RestTemplate restTemplate;

    public UserAchievementService(@Value("${endpoint.user-service.url}") String userServiceUrl, RestTemplate restTemplate) {
        this.userServiceUrl = userServiceUrl;
        this.restTemplate = restTemplate;
    }

    public List<AchievementDto> getAllAchievements(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/achievements";
        var dtos = restTemplate.getForObject(url, AchievementDto[].class);
        return Arrays.asList(Objects.requireNonNull(dtos));
    }

    public void addAchievement(long userId, long achievementId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/achievements";
        restTemplate.postForObject(url, achievementId, UserDto.class);
    }

    public void deleteAllAchievements(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/achievements";
        restTemplate.delete(url);
    }

    public void deleteAchievement(long userId, long achievementId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/achievements/" + achievementId;
        restTemplate.delete(url);
    }
}
