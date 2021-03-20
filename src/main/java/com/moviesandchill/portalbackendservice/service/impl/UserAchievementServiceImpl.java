package com.moviesandchill.portalbackendservice.service.impl;

import com.moviesandchill.portalbackendservice.dto.achievement.AchievementDto;
import com.moviesandchill.portalbackendservice.exception.achievement.AchievementNotFoundException;
import com.moviesandchill.portalbackendservice.exception.user.UserNotFoundException;
import com.moviesandchill.portalbackendservice.service.UserAchievementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserAchievementServiceImpl implements UserAchievementService {

    private String userServiceUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<AchievementDto> getAllAchievements(long userId) throws UserNotFoundException {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/achievements";
        AchievementDto[] dtos = restTemplate.getForObject(url, AchievementDto[].class);

        if (dtos == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(dtos));
    }

    @Override
    public void addAchievement(long userId, long achievementId) throws UserNotFoundException, AchievementNotFoundException {

    }

    @Override
    public void deleteAllAchievements(long userId) throws UserNotFoundException {

    }

    @Override
    public void deleteAchievement(long userId, long achievementId) throws AchievementNotFoundException, UserNotFoundException {

    }

    @Autowired
    public void setUserServiceUrl(@Value("${endpoint.user-service.url}") String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }
}
