package com.moviesandchill.portalbackendservice.controller;

import com.moviesandchill.portalbackendservice.dto.user.user.NewUserDto;
import com.moviesandchill.portalbackendservice.dto.user.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.security.JwtTokenProvider;
import com.moviesandchill.portalbackendservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController()
@RequestMapping(
        path = "api/v1/public/auth",
        produces = "application/json"
)
@Slf4j
public class PublicAuthController {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    public PublicAuthController(AuthService authService, JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //TODO логику вынести в из контроллера?
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res) {
        var userIdOptional = authService.login(loginRequestDto);
        if (userIdOptional.isEmpty()) {
            return createBadRequest();
        }

        var userId = userIdOptional.get();
        var token = jwtTokenProvider.createToken(userId);
        jwtTokenProvider.setTokenToResponse(res, token);

        var jsonMap = Map.of("userId", userId);
        return ResponseEntity.ok(jsonMap);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody NewUserDto newUserDto, HttpServletResponse res) {
        var userIdOptional = authService.register(newUserDto);
        if (userIdOptional.isEmpty()) {
            return createBadRequest();
        }

        var userId = userIdOptional.get();
        var token = jwtTokenProvider.createToken(userId);
        jwtTokenProvider.setTokenToResponse(res, token);

        var jsonMap = Map.of("userId", userId);
        return ResponseEntity.ok(jsonMap);
    }

    private ResponseEntity<?> createBadRequest() {
        return ResponseEntity.badRequest()
                .build();
    }
}
