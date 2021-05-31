package com.moviesandchill.portalbackendservice.controller.user;

import com.moviesandchill.portalbackendservice.dto.user.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.dto.user.password.UpdatePasswordDto;
import com.moviesandchill.portalbackendservice.dto.user.user.FullUserDto;
import com.moviesandchill.portalbackendservice.dto.user.user.NewUserDto;
import com.moviesandchill.portalbackendservice.dto.user.user.UpdateUserDto;
import com.moviesandchill.portalbackendservice.dto.user.user.UserDto;
import com.moviesandchill.portalbackendservice.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Secured("ROLE_USER")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @GetMapping("full/{userId}")
    public FullUserDto getFullUser(@PathVariable long userId) {
        return userService.getFullUser(userId);
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable long userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public UserDto addUser(@RequestBody NewUserDto newUserDto) {
        return userService.addUser(newUserDto);
    }

    @PutMapping("/{userId}")
    @Secured("ROLE_USER")
    public void updateUser(@PathVariable long userId, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUser(userId, updateUserDto);
    }

    @DeleteMapping("/{userId}")
    @Secured("ROLE_USER")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }


    @PutMapping("/{userId}/password")
    @Secured("ROLE_USER")
    public void updateUserPassword(@PathVariable long userId, @RequestBody UpdatePasswordDto updatePasswordDto) {
        userService.updateUserPassword(userId, updatePasswordDto);
    }

    @PostMapping(path = "/{userId}/logo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Secured("ROLE_USER")
    public void updateUserLogo(@PathVariable long userId, @RequestPart("file") MultipartFile file) {
        userService.updateUserLogo(userId, file);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody NewUserDto newUserDto) {
        return userService.register(newUserDto);
    }

    @PostMapping("/{userId}/ban")
    @Secured("ROLE_USER")
    public void ban(@PathVariable long userId) {
        userService.ban(userId);
    }

    @PostMapping("/{userId}/unban")
    @Secured("ROLE_USER")
    public void unban(@PathVariable long userId) {
        userService.unban(userId);
    }
}
