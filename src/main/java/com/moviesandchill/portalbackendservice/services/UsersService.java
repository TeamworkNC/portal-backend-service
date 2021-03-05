package com.moviesandchill.portalbackendservice.services;

import com.moviesandchill.portalbackendservice.dto.UserDto;
import com.moviesandchill.portalbackendservice.dto.login.LoginRequestDto;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<UserDto> getAllUsers();

    Optional<UserDto> getUserById(long userId);

    Optional<UserDto> login(LoginRequestDto loginRequestDto);
}
