package com.moviesandchill.portalbackendservice.service.impl;

import com.moviesandchill.portalbackendservice.dto.user.UserDto;
import com.moviesandchill.portalbackendservice.exception.user.UserNotFoundException;
import com.moviesandchill.portalbackendservice.service.UserFriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserFriendServiceImpl implements UserFriendService {

    private String userServiceUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<UserDto> getAllFriends(long userId) throws UserNotFoundException {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/friends";
        UserDto[] dtos = restTemplate.getForObject(url, UserDto[].class);

        if (dtos == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(dtos));
    }

    @Override
    public void addFriend(long userId, long friendId) throws UserNotFoundException {

    }

    @Override
    public void deleteAllFriends(long userId) throws UserNotFoundException {

    }

    @Override
    public void deleteFriend(long userId, long friendId) throws UserNotFoundException {

    }

    @Autowired
    public void setUserServiceUrl(@Value("${endpoint.user-service.url}") String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }
}
