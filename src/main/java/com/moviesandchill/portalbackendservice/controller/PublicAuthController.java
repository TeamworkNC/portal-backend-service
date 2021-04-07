package com.moviesandchill.portalbackendservice.controller;

import com.moviesandchill.portalbackendservice.dto.user.NewUserDto;
import com.moviesandchill.portalbackendservice.dto.user.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.mapper.CommonMapper;
import com.moviesandchill.portalbackendservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(
        path = "api/v1/public/auth",
        produces = "application/json"
)
@Slf4j
public class PublicAuthController {

    private final AuthService authService;
    private final CommonMapper commonMapper;

    public PublicAuthController(AuthService authService, CommonMapper commonMapper) {
        this.authService = authService;
        this.commonMapper = commonMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody LoginRequestDto loginRequestDto) {
        var userOptional = authService.login(loginRequestDto);
        return commonMapper.toResponseEntity(userOptional);
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody NewUserDto newUserDto) {
        var userOptional = authService.register(newUserDto);
        return commonMapper.toResponseEntity(userOptional);
    }
}
