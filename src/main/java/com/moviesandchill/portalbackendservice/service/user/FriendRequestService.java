package com.moviesandchill.portalbackendservice.service.user;

import com.moviesandchill.portalbackendservice.dto.chat.notification.NewNotificationDto;
import com.moviesandchill.portalbackendservice.dto.user.friendrequest.FriendRequestDto;
import com.moviesandchill.portalbackendservice.dto.user.friendrequest.NewFriendRequestDto;
import com.moviesandchill.portalbackendservice.service.chat.NotificationService;
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
    private final NotificationService notificationService;

    public FriendRequestService(@Value("${endpoint.user-service.url}") String userServiceUrl, RestTemplate restTemplate, NotificationService notificationService) {
        this.userServiceUrl = userServiceUrl;
        this.restTemplate = restTemplate;
        this.notificationService = notificationService;
    }

    public List<FriendRequestDto> getAllFriendRequests() {
        String url = userServiceUrl + "/api/v1/friend_requests";
        var dtos = restTemplate.getForObject(url, FriendRequestDto[].class);
        return Arrays.asList(Objects.requireNonNull(dtos));
    }

    public FriendRequestDto addFriendRequest(NewFriendRequestDto newFriendRequestDto) {
        String url = userServiceUrl + "/api/v1/friend_requests";
        long userId = newFriendRequestDto.getUserId();
        long recipientId = newFriendRequestDto.getRecipientId();
        var friendRequestDto = restTemplate.postForObject(url, newFriendRequestDto, FriendRequestDto.class);
        notificationService.addNotification(new NewNotificationDto(recipientId, "user " + userId + " send friend request"));
        return friendRequestDto;
    }

    public void deleteAllFriendRequests() {
        String url = userServiceUrl + "/api/v1/friend_requests";
        restTemplate.delete(url);
    }
}
