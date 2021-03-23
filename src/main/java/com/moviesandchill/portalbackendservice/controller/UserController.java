package com.moviesandchill.portalbackendservice.controller;

import com.moviesandchill.portalbackendservice.dto.password.UpdatePasswordDto;
import com.moviesandchill.portalbackendservice.dto.user.FullUserDto;
import com.moviesandchill.portalbackendservice.dto.user.NewUserDto;
import com.moviesandchill.portalbackendservice.dto.user.UserDto;
import com.moviesandchill.portalbackendservice.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users",
        produces = "application/json"
)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public UserDto addUser(@RequestBody NewUserDto newUserDto) {
        return userService.addUser(newUserDto);
    }

    @DeleteMapping
    @Secured("ROLE_ADMIN")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @GetMapping("/{userId}")
    public FullUserDto getUser(@PathVariable long userId) {
        return userService.getFullUser(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }


    @PutMapping("/{userId}/password")
    public boolean updateUserPassword(@PathVariable long userId, @RequestBody UpdatePasswordDto updatePasswordDto) {
        return userService.updateUserPassword(userId, updatePasswordDto);
    }
}
