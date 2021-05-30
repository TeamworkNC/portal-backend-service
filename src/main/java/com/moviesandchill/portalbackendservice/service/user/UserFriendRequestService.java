package com.moviesandchill.portalbackendservice.service.user;

import com.moviesandchill.portalbackendservice.dto.user.friendrequest.FriendRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserFriendRequestService {

    private final String userServiceUrl;
    private final RestTemplate restTemplate;

    public UserFriendRequestService(@Value("${endpoints.user-service-url}") String userServiceUrl, RestTemplate restTemplate) {
        this.userServiceUrl = userServiceUrl;
        this.restTemplate = restTemplate;
    }

    public List<FriendRequestDto> getAllFriendRequests(long userId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/friend_requests";
        var dtos = restTemplate.getForObject(url, FriendRequestDto[].class);
        return Arrays.asList(Objects.requireNonNull(dtos));
    }

    public void acceptFriendRequest(long userId, long friendRequestId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/friend_requests/" + friendRequestId + "/accept";
        restTemplate.postForObject(url, Void.class, Void.class);
    }

    public void declineFriendRequest(long userId, long friendRequestId) {
        String url = userServiceUrl + "/api/v1/users/" + userId + "/friend_requests/" + friendRequestId + "/decline";
        restTemplate.postForObject(url, Void.class, Void.class);
    }
}

