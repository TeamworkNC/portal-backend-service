package com.moviesandchill.portalbackendservice.controller.user;

import com.moviesandchill.portalbackendservice.dto.user.user.FullUserDto;
import com.moviesandchill.portalbackendservice.dto.user.user.NewUserDto;
import com.moviesandchill.portalbackendservice.dto.user.user.UserDto;
import com.moviesandchill.portalbackendservice.dto.user.password.UpdatePasswordDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.service.user.UserService;
import org.springframework.http.ResponseEntity;
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
    private final CommonMapper commonMapper;

    public UserController(UserService userService, CommonMapper commonMapper) {
        this.userService = userService;
        this.commonMapper = commonMapper;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserDto> addUser(@RequestBody NewUserDto newUserDto) {
        return commonMapper.toResponseEntity(userService.addUser(newUserDto));
    }

    @DeleteMapping
    @Secured("ROLE_ADMIN")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<FullUserDto> getUser(@PathVariable long userId) {
        return commonMapper.toResponseEntity(userService.getFullUser(userId));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        commonMapper.toResponseEntity(userService.deleteUser(userId));
    }

    @PutMapping("/{userId}/password")
    public boolean updateUserPassword(@PathVariable long userId, @RequestBody UpdatePasswordDto updatePasswordDto) {
        return userService.updateUserPassword(userId, updatePasswordDto);
    }
}
