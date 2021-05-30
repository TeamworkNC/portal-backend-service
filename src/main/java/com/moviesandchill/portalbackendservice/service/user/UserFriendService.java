package com.moviesandchill.portalbackendservice.service.user;

import com.moviesandchill.portalbackendservice.dto.user.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserFriendService {

    private final String userServiceUrl;
    private final RestTemplate restTemplate;

    public UserFriendService(@Value("${endpoints.user-service-url}") String userServiceUrl, RestTemplate restTemplate) {
        this.userServiceUrl = userServiceUrl;
        this.restTemplate = restTemplate;
    }

    public List<UserDto> getAllFriends(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/friends";
        var dtos = restTemplate.getForObject(url, UserDto[].class);
        return Arrays.asList(Objects.requireNonNull(dtos));
    }

    public void addFriend(long userId, long friendId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/friends";
        restTemplate.postForObject(url, friendId, Void.class);
    }

    public void deleteAllFriends(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/friends";
        restTemplate.delete(url);
    }

    public void deleteFriend(long userId, long friendId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/friends/" + friendId;
        restTemplate.delete(url);
    }
}
