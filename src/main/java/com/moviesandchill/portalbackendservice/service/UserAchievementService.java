package com.moviesandchill.portalbackendservice.service;

import com.moviesandchill.portalbackendservice.dto.achievement.AchievementDto;
import com.moviesandchill.portalbackendservice.exception.achievement.AchievementNotFoundException;
import com.moviesandchill.portalbackendservice.exception.user.UserNotFoundException;

import java.util.List;

public interface UserAchievementService {
    List<AchievementDto> getAllAchievements(long userId) throws UserNotFoundException;

    void addAchievement(long userId, long achievementId) throws UserNotFoundException, AchievementNotFoundException;

    void deleteAllAchievements(long userId) throws UserNotFoundException;

    void deleteAchievement(long userId, long achievementId) throws AchievementNotFoundException, UserNotFoundException;
}
