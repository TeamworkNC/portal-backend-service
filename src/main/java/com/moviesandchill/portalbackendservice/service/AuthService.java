package com.moviesandchill.portalbackendservice.service;

import com.moviesandchill.portalbackendservice.dto.user.NewUserDto;
import com.moviesandchill.portalbackendservice.dto.user.UserDto;
import com.moviesandchill.portalbackendservice.dto.user.login.LoginRequestDto;

import java.util.Optional;

public interface AuthService {
    Optional<UserDto> login(LoginRequestDto loginRequestDto);

    Optional<Long> register(NewUserDto newUserDto);

    Optional<UserDto> getCurrentUser();
}
