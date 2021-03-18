package com.moviesandchill.portalbackendservice.service;

import com.moviesandchill.portalbackendservice.dto.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.dto.user.UserDto;

import java.util.Optional;

public interface AuthService {
    Optional<UserDto> login(LoginRequestDto loginRequestDto);

    Optional<UserDto> getCurrentUser();
}
