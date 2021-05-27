package com.moviesandchill.portalbackendservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.moviesandchill.portalbackendservice.dto.user.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.dto.user.user.NewUserDto;
import com.moviesandchill.portalbackendservice.security.JwtTokenProvider;
import com.moviesandchill.portalbackendservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController()
@RequestMapping(
        path = "api/v1/public/auth",
        produces = "application/json"
)
@Slf4j
public class PublicAuthController {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PublicAuthController(AuthService authService, JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //TODO логику вынести в из контроллера?
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res) {
        var userId = authService.login(loginRequestDto);
        var token = jwtTokenProvider.createToken(userId);


        ObjectNode json = objectMapper.createObjectNode();
        json.put("userId", userId);
        json.put("token", token);

        return ResponseEntity.ok(json);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody NewUserDto newUserDto, HttpServletResponse res) {
        var userIdOptional = authService.register(newUserDto);
        if (userIdOptional.isEmpty()) {
            return createBadRequest();
        }

        var userId = userIdOptional.get();
        var token = jwtTokenProvider.createToken(userId);

        ObjectNode json = objectMapper.createObjectNode();
        json.put("userId", userId);
        json.put("token", token);

        return ResponseEntity.ok(json);
    }

    private ResponseEntity<?> createBadRequest() {
        return ResponseEntity.badRequest()
                .build();
    }
}
