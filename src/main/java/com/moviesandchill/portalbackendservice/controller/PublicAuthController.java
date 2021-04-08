package com.moviesandchill.portalbackendservice.controller;

import com.moviesandchill.portalbackendservice.dto.user.NewUserDto;
import com.moviesandchill.portalbackendservice.dto.user.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
@RequestMapping(
        path = "api/v1/public/auth",
        produces = "application/json"
)
@Slf4j
public class PublicAuthController {

    private final AuthService authService;

    public PublicAuthController(AuthService authService) {
        this.authService = authService;
    }

    //TODO логику вынести в из контроллера?
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        var userIdOptional = authService.login(loginRequestDto);
        if (userIdOptional.isPresent()) {
            Long userId = userIdOptional.get();
            var jsonMap = Map.of("userId", userId);
            return ResponseEntity.ok(jsonMap);
        }
        return createBadRequest();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody NewUserDto newUserDto) {
        var userIdOptional = authService.register(newUserDto);
        if (userIdOptional.isPresent()) {
            Long userId = userIdOptional.get();
            var jsonMap = Map.of("userId", userId);
            return ResponseEntity.ok(jsonMap);
        }
        return createBadRequest();
    }

    private ResponseEntity<?> createBadRequest() {
        return ResponseEntity.badRequest()
                .build();
    }
}
