package com.moviesandchill.portalbackendservice.service.user;


import com.moviesandchill.portalbackendservice.dto.user.achievement.AchievementDto;
import com.moviesandchill.portalbackendservice.dto.user.achievement.NewAchievementDto;
import com.moviesandchill.portalbackendservice.dto.user.achievement.UpdateAchievementDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AchievementService {

    private final String userServiceUrl;
    private final RestTemplate restTemplate;

    public AchievementService(@Value("${endpoint.user-service.url}") String userServiceUrl, RestTemplate restTemplate) {
        this.userServiceUrl = userServiceUrl;
        this.restTemplate = restTemplate;
    }

    public List<AchievementDto> getAllAchievements() {
        String url = userServiceUrl + "/api/v1/achievements";
        var dtos = restTemplate.getForObject(url, AchievementDto[].class);
        return Arrays.asList(Objects.requireNonNull(dtos));
    }

    public AchievementDto addAchievement(NewAchievementDto newAchievementDto) {
        String url = userServiceUrl + "/api/v1/achievements";
        return restTemplate.postForObject(url, newAchievementDto, AchievementDto.class);
    }

    public void deleteAllAchievements() {
        String url = userServiceUrl + "/api/v1/achievements";
        restTemplate.delete(url);
    }

    public AchievementDto getAchievement(long achievementId) {
        String url = userServiceUrl + "/api/v1/achievements/" + achievementId;
        return restTemplate.getForObject(url, AchievementDto.class);
    }

    public void updateAchievement(long achievementId, UpdateAchievementDto updateAchievementDto) {
        String url = userServiceUrl + "/api/v1/achievements/" + achievementId;
        restTemplate.put(url, updateAchievementDto);
    }

    public void deleteAchievement(long achievementId) {
        String url = userServiceUrl + "/api/v1/achievements/" + achievementId;
        restTemplate.delete(url);
    }
}
