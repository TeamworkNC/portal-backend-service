package com.moviesandchill.portalbackendservice.controller.user;

import com.moviesandchill.portalbackendservice.dto.user.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.dto.user.password.UpdatePasswordDto;
import com.moviesandchill.portalbackendservice.dto.user.user.FullUserDto;
import com.moviesandchill.portalbackendservice.dto.user.user.NewUserDto;
import com.moviesandchill.portalbackendservice.dto.user.user.UpdateUserDto;
import com.moviesandchill.portalbackendservice.dto.user.user.UserDto;
import com.moviesandchill.portalbackendservice.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users",
        produces = "application/json"
)
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @GetMapping("/{userId}")
    public FullUserDto getUserById(@PathVariable long userId) {
        return userService.getFullUser(userId);
    }

    @PostMapping
    public UserDto addUser(@RequestBody NewUserDto newUserDto) {
        return userService.addUser(newUserDto);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable long userId, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUser(userId, updateUserDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }


    @PutMapping("/{userId}/password")
    public void updateUserPassword(@PathVariable long userId, @RequestBody UpdatePasswordDto updatePasswordDto) {
        userService.updateUserPassword(userId, updatePasswordDto);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody NewUserDto newUserDto) {
        return userService.register(newUserDto);
    }
}