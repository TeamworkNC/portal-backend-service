package com.moviesandchill.portalbackendservice.services;

import com.moviesandchill.portalbackendservice.dto.UserDto;
import com.moviesandchill.portalbackendservice.dto.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.exceptions.user.UserNotFoundException;
import com.moviesandchill.portalbackendservice.security.SimpleAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UsersService usersService;

    public AuthServiceImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public Optional<UserDto> login(LoginRequestDto loginRequestDto) {
        var userOptional = usersService.login(loginRequestDto);

        if (userOptional.isEmpty()) {
            log.info("user not found");
            return Optional.empty();
        }

        UserDto user = userOptional.get();
        long userId = user.getUserId();
        log.info("user id: " + userId);

        Authentication authentication = new SimpleAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return Optional.of(user);
    }

    @Override
    public UserDto login(long userId) throws UserNotFoundException {
        UserDto userDto = usersService.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Authentication authentication = new SimpleAuthentication(userDto);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return userDto;
    }

    @Override
    public Optional<UserDto> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = (UserDto) authentication.getPrincipal(); //может быть null
        return Optional.ofNullable(user);
    }
}
