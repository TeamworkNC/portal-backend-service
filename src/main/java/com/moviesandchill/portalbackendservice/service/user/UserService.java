package com.moviesandchill.portalbackendservice.service.user;

import com.moviesandchill.portalbackendservice.dto.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.dto.password.UpdatePasswordDto;
import com.moviesandchill.portalbackendservice.dto.user.FullUserDto;
import com.moviesandchill.portalbackendservice.dto.user.NewUserDto;
import com.moviesandchill.portalbackendservice.dto.user.UpdateUserDto;
import com.moviesandchill.portalbackendservice.dto.user.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();

    Optional<UserDto> addUser(NewUserDto user);

    boolean deleteAllUsers();

    Optional<UserDto> getUser(long userId);

    Optional<FullUserDto> getFullUser(long userId);

    boolean updateUser(long userId, UpdateUserDto updateUserDto);

    boolean deleteUser(long userId);

    boolean updateUserPassword(long userId, UpdatePasswordDto updatePasswordDto);

    Optional<UserDto> login(LoginRequestDto loginRequestDto);

    Optional<UserDto> register(NewUserDto newUserDto);
}
