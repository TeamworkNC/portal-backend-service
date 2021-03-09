package com.moviesandchill.portalbackendservice.services;

import com.moviesandchill.portalbackendservice.dto.UserDto;
import com.moviesandchill.portalbackendservice.dto.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.exceptions.user.UserNotFoundException;

import java.util.Optional;

public interface AuthService {
    Optional<UserDto> login(LoginRequestDto loginRequestDto);

    UserDto login(long userId) throws UserNotFoundException;

    Optional<UserDto> getCurrentUser();
}
