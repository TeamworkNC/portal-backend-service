package com.moviesandchill.portalbackendservice.service.impl.user;

import com.moviesandchill.portalbackendservice.dto.user.achievement.AchievementDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.service.user.UserAchievementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class UserAchievementServiceImpl implements UserAchievementService {

    private String userServiceUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    private final CommonMapper commonMapper;

    public UserAchievementServiceImpl(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }

    @Override
    public List<AchievementDto> getAllAchievements(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/achievements";
        AchievementDto[] dtos = restTemplate.getForObject(url, AchievementDto[].class);
        return commonMapper.toList(dtos);
    }

    @Override
    public boolean addAchievement(long userId, long achievementId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteAllAchievements(long userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteAchievement(long userId, long achievementId) {
        throw new UnsupportedOperationException();
    }

    @Autowired
    public void setUserServiceUrl(@Value("${endpoint.user-service.url}") String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }
}
