package com.moviesandchill.portalbackendservice.controller.user;

import com.moviesandchill.portalbackendservice.dto.user.globalrole.GlobalRoleDto;
import com.moviesandchill.portalbackendservice.service.user.UserGlobalRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users/{userId}/global-roles",
        produces = "application/json"
)
@Slf4j
public class UserGlobalRoleController {

    private final UserGlobalRoleService userGlobalRoleService;

    public UserGlobalRoleController(UserGlobalRoleService globalRoleService) {
        this.userGlobalRoleService = globalRoleService;
    }

    @GetMapping
    public List<GlobalRoleDto> getAllGlobalRoles(@PathVariable long userId) {
        return userGlobalRoleService.getAllGlobalRoles(userId);
    }

    @PostMapping
    public void addGlobalRole(@PathVariable long userId, @RequestBody long globalRoleId) {
        userGlobalRoleService.addGlobalRole(userId, globalRoleId);
    }

    @DeleteMapping
    public void deleteAllGlobalRoles(@PathVariable long userId) {
        userGlobalRoleService.deleteAllGlobalRoles(userId);
    }

    @DeleteMapping("/{globalRoleId}")
    public void deleteGlobalRole(@PathVariable long userId, @PathVariable long globalRoleId) {
        userGlobalRoleService.deleteGlobalRole(userId, globalRoleId);
    }
}
