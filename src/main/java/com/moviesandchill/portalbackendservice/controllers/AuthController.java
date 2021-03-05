package com.moviesandchill.portalbackendservice.controllers;

import com.moviesandchill.portalbackendservice.dto.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController()
@RequestMapping(
        path = "api/v1/public/auth",
        produces = "application/json"
)
@Slf4j
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res) {
        authService.login(loginRequestDto).ifPresent(token -> {
            Cookie cookie = new Cookie("session_token", token);
            cookie.setMaxAge(9999999);
            res.addCookie(cookie);
        });
    }
}
