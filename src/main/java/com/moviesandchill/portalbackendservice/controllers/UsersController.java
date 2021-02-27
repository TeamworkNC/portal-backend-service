package com.moviesandchill.portalbackendservice.controllers;

import com.moviesandchill.portalbackendservice.dto.UserDto;
import com.moviesandchill.portalbackendservice.services.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users",
        produces = "application/json"
)
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return usersService.getAllUsers();
    }
}
