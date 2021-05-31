package com.moviesandchill.portalbackendservice.controller.user;

import com.moviesandchill.portalbackendservice.dto.user.achievement.AchievementDto;
import com.moviesandchill.portalbackendservice.dto.user.achievement.NewAchievementDto;
import com.moviesandchill.portalbackendservice.dto.user.achievement.UpdateAchievementDto;
import com.moviesandchill.portalbackendservice.service.user.AchievementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
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
    @Secured("ROLE_USER")
    private AchievementDto addAchievement(@RequestBody NewAchievementDto newAchievementDto) {
        return achievementService.addAchievement(newAchievementDto);
    }

    @DeleteMapping
    @Secured("ROLE_USER")
    private void deleteAllAchievements() {
        achievementService.deleteAllAchievements();
    }

    @GetMapping("/{achievementId}")
    private AchievementDto getAchievement(@PathVariable long achievementId) {
        return achievementService.getAchievement(achievementId);
    }

    @PutMapping("/{achievementId}")
    @Secured("ROLE_USER")
    public void updateAchievement(@PathVariable long achievementId, @RequestBody UpdateAchievementDto updateAchievementDto) {
        achievementService.updateAchievement(achievementId, updateAchievementDto);
    }

    @DeleteMapping("/{achievementId}")
    @Secured("ROLE_USER")
    private void deleteAchievement(@PathVariable long achievementId) {
        achievementService.deleteAchievement(achievementId);
    }
}
