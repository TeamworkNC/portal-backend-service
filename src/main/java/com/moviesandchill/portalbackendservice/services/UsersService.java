package com.moviesandchill.portalbackendservice.services;

import com.moviesandchill.portalbackendservice.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> getAllUsers();
}
