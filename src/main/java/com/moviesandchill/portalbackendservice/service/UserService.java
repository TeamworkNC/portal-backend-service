package com.moviesandchill.portalbackendservice.service;

import com.moviesandchill.portalbackendservice.dto.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.dto.password.UpdatePasswordDto;
import com.moviesandchill.portalbackendservice.dto.user.FullUserDto;
import com.moviesandchill.portalbackendservice.dto.user.NewUserDto;
import com.moviesandchill.portalbackendservice.dto.user.UpdateUserDto;
import com.moviesandchill.portalbackendservice.dto.user.UserDto;
import com.moviesandchill.portalbackendservice.exception.user.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto addUser(NewUserDto user);

    void deleteAllUsers();

    UserDto getUser(long userId) throws UserNotFoundException;

    FullUserDto getFullUser(long userId) throws UserNotFoundException;

    void updateUser(long userId, UpdateUserDto updateUserDto) throws UserNotFoundException;

    void deleteUser(long userId);

    boolean updateUserPassword(long userId, UpdatePasswordDto updatePasswordDto) throws UserNotFoundException;

    Optional<UserDto> login(LoginRequestDto loginRequestDto);

    Optional<UserDto> register(NewUserDto newUserDto);
}
