package com.moviesandchill.portalbackendservice.services;

import com.moviesandchill.portalbackendservice.dto.UserDto;
import com.moviesandchill.portalbackendservice.dto.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.security.SimpleAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UsersService usersService;

    public AuthServiceImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    private final Map<String, Long> sessions = new HashMap<>();

    @Override
    public Optional<String> login(LoginRequestDto loginRequestDto) {
        log.info(String.valueOf(loginRequestDto));
        var userOptional = usersService.login(loginRequestDto);

        if (userOptional.isEmpty()) {
            log.info("user not found");
            return Optional.empty();
        }

        UserDto user = userOptional.get();
        long userId = user.getUserId();
        log.info("user id: " + userId);

        String token = createSession(userId);
        log.info("new token: " + token);

        return Optional.of(token);
    }


    private synchronized String createSession(long userId) {
        String token = generateToken();
        log.info(String.valueOf(sessions));
        sessions.put(token, userId);
        log.info(String.valueOf(sessions));
        return token;
    }

    private String generateToken() {
        Random random = new Random();
        long result = random.nextLong();
        return Long.toString(result);
    }

    @Override
    public boolean authorize(String token) {
        log.info("try authorize by token: " + token);
        log.info(String.valueOf(sessions));

        if (sessions.containsKey(token)) {
            log.info("session found");
            long userId = sessions.get(token);
            UserDto userDto = usersService.getUserById(userId).orElseThrow();
            Authentication authentication = new SimpleAuthentication(userDto);
            log.info(String.valueOf(authentication));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        }
        log.info("session not found");
        return false;
    }

    @Override
    public Optional<UserDto> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = (UserDto) authentication.getPrincipal(); //может быть null
        return Optional.ofNullable(user);
    }
}
