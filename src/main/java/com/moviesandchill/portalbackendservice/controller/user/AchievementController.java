package com.moviesandchill.portalbackendservice.controller.user;

import com.moviesandchill.portalbackendservice.dto.user.achievement.AchievementDto;
import com.moviesandchill.portalbackendservice.dto.user.achievement.NewAchievementDto;
import com.moviesandchill.portalbackendservice.dto.user.achievement.UpdateAchievementDto;
import com.moviesandchill.portalbackendservice.service.user.AchievementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/achievements",
        produces = "application/json"
)
@Slf4j
public class AchievementController {

    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping
    private List<AchievementDto> getAllAchievements() {
        return achievementService.getAllAchievements();
    }

    @PostMapping
    private AchievementDto addAchievement(@RequestBody NewAchievementDto newAchievementDto) {
        return achievementService.addAchievement(newAchievementDto);
    }

    @DeleteMapping
    private void deleteAllAchievements() {
        achievementService.deleteAllAchievements();
    }

    @GetMapping("/{achievementId}")
    private AchievementDto getAchievement(@PathVariable long achievementId) {
        return achievementService.getAchievement(achievementId);
    }

    @PutMapping("/{achievementId}")
    public void updateAchievement(@PathVariable long achievementId, @RequestBody UpdateAchievementDto updateAchievementDto) {
        achievementService.updateAchievement(achievementId, updateAchievementDto);
    }

    @DeleteMapping("/{achievementId}")
    private void deleteAchievement(@PathVariable long achievementId) {
        achievementService.deleteAchievement(achievementId);
    }
}
