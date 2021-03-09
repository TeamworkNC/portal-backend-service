package com.moviesandchill.portalbackendservice.services;

import com.moviesandchill.portalbackendservice.dto.UserDto;
import com.moviesandchill.portalbackendservice.dto.login.LoginRequestDto;
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
public class UsersServiceImpl implements UsersService {

    @Value("${endpoint.user-service.url}")
    private String USER_SERVICE_URL;

    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public List<UserDto> getAllUsers() {
        String url = USER_SERVICE_URL + "/api/v1/users";
        UserDto[] dtos = restTemplate.getForObject(url, UserDto[].class);

        if (dtos == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(dtos));
    }

    @Override
    public Optional<UserDto> getUserById(long userId) {
        String url = USER_SERVICE_URL + "/api/v1/users/" + userId;
        UserDto dto = restTemplate.getForObject(url, UserDto.class);
        return Optional.ofNullable(dto);
    }

    @Override
    public Optional<UserDto> login(LoginRequestDto loginRequestDto) {
        String url = USER_SERVICE_URL + "/api/v1/users/login";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LoginRequestDto> entity = new HttpEntity<>(loginRequestDto, headers);
        UserDto dto = restTemplate.postForObject(url, entity, UserDto.class);
        return Optional.ofNullable(dto);
    }
}
