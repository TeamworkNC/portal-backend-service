package com.moviesandchill.portalbackendservice.services;

import com.moviesandchill.portalbackendservice.dto.UserDto;
import com.moviesandchill.portalbackendservice.dto.login.LoginRequestDto;

import java.util.Optional;

public interface AuthService {
    /**
     * @param loginRequestDto dto с логином и паролем
     * @return токен
     */
    Optional<String> login(LoginRequestDto loginRequestDto);

    boolean authorize(String token);

    Optional<UserDto> getCurrentUser();
}
