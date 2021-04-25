package com.moviesandchill.portalbackendservice.controller.user;


import com.moviesandchill.portalbackendservice.dto.user.achievement.AchievementDto;
import com.moviesandchill.portalbackendservice.service.user.UserAchievementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users/{userId}/achievements",
        produces = "application/json"
)
@Slf4j
public class UserAchievementController {

    private final UserAchievementService userAchievementService;


    public UserAchievementController(UserAchievementService userAchievementService) {
        this.userAchievementService = userAchievementService;
    }

    @GetMapping
    List<AchievementDto> getAllAchievements(@PathVariable long userId) {
        return userAchievementService.getAllAchievements(userId);
    }

    @PostMapping
    void addAchievement(@PathVariable long userId, @RequestBody long achievementId) {
        userAchievementService.addAchievement(userId, achievementId);
    }

    @DeleteMapping
    void deleteAllAchievements(@PathVariable long userId) {
        userAchievementService.deleteAllAchievements(userId);
    }

    @DeleteMapping("/{achievementId}")
    void deleteAchievement(@PathVariable long userId, @PathVariable long achievementId) {
        userAchievementService.deleteAchievement(userId, achievementId);
    }
}
