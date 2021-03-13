package com.moviesandchill.portalbackendservice.services;

import com.moviesandchill.portalbackendservice.dto.UserDto;
import com.moviesandchill.portalbackendservice.dto.login.LoginRequestDto;

import java.util.Optional;

public interface AuthService {
    Optional<UserDto> login(LoginRequestDto loginRequestDto);
}
