package com.moviesandchill.portalbackendservice.service.user;

import com.moviesandchill.portalbackendservice.dto.user.friendrequest.FriendRequestDto;
import com.moviesandchill.portalbackendservice.dto.user.friendrequest.NewFriendRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class FriendRequestService {

    private final String userServiceUrl;
    private final RestTemplate restTemplate;

    public FriendRequestService(@Value("${endpoint.user-service.url}") String userServiceUrl, RestTemplate restTemplate) {
        this.userServiceUrl = userServiceUrl;
        this.restTemplate = restTemplate;
    }

    public List<FriendRequestDto> getAllFriendRequests() {
        String url = userServiceUrl + "/api/v1/friend_requests";
        var dtos = restTemplate.getForObject(url, FriendRequestDto[].class);
        return Arrays.asList(Objects.requireNonNull(dtos));
    }

    public FriendRequestDto addFriendRequest(NewFriendRequestDto newFriendRequestDto) {
        String url = userServiceUrl + "/api/v1/friend_requests";
        return restTemplate.postForObject(url, newFriendRequestDto, FriendRequestDto.class);
    }

    public void deleteAllFriendRequests() {
        String url = userServiceUrl + "/api/v1/friend_requests";
        restTemplate.delete(url);
    }
}
