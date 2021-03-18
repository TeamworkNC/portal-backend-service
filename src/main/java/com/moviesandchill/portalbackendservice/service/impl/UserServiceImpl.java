package com.moviesandchill.portalbackendservice.service.impl;

import com.moviesandchill.portalbackendservice.dto.login.LoginRequestDto;
import com.moviesandchill.portalbackendservice.dto.user.UserDto;
import com.moviesandchill.portalbackendservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private String userServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public List<UserDto> getAllUsers() {
        String url = userServiceUrl + "/api/v1/users";
        UserDto[] dtos = restTemplate.getForObject(url, UserDto[].class);

        if (dtos == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(dtos));
    }

    @Override
    public Optional<UserDto> getUserById(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId;
        UserDto dto = restTemplate.getForObject(url, UserDto.class);
        return Optional.ofNullable(dto);
    }

    @Override
    public Optional<UserDto> login(LoginRequestDto loginRequestDto) {
        String url = userServiceUrl + "/api/v1/users/login";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LoginRequestDto> entity = new HttpEntity<>(loginRequestDto, headers);
        UserDto dto = restTemplate.postForObject(url, entity, UserDto.class);
        return Optional.ofNullable(dto);
    }

    @Autowired
    public void setUserServiceUrl(@Value("${endpoint.user-service.url}") String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }
}
