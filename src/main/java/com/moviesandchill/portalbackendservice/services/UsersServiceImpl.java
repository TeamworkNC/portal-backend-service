package com.moviesandchill.portalbackendservice.services;

import com.moviesandchill.portalbackendservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Value("${endpoint.user-service.url}")
    private String USER_SERVICE_URL;

    @Override
    public List<UserDto> getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<UserDto>> responseEntity =
                restTemplate.exchange(
                        USER_SERVICE_URL + "/api/v1/users",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<UserDto>>() {
                        }
                );
        return responseEntity.getBody();
    }
}
