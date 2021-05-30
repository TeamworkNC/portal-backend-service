package com.moviesandchill.portalbackendservice.service.user;

import com.moviesandchill.portalbackendservice.dto.chat.notification.NewNotificationDto;
import com.moviesandchill.portalbackendservice.dto.user.friendrequest.FriendRequestDto;
import com.moviesandchill.portalbackendservice.dto.user.friendrequest.NewFriendRequestDto;
import com.moviesandchill.portalbackendservice.service.chat.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class FriendRequestService {

    private String userServiceUrl;
    private RestTemplate restTemplate;
    private NotificationService notificationService;
    private UserService userService;


    public List<FriendRequestDto> getAllFriendRequests() {
        String url = userServiceUrl + "/api/v1/friend_requests";
        var dtos = restTemplate.getForObject(url, FriendRequestDto[].class);
        return Arrays.asList(Objects.requireNonNull(dtos));
    }

    public FriendRequestDto addFriendRequest(NewFriendRequestDto newFriendRequestDto) {
        String url = userServiceUrl + "/api/v1/friend_requests";
        long senderId = newFriendRequestDto.getUserId();
        long recipientId = newFriendRequestDto.getRecipientId();

        var friendRequestDto = restTemplate.postForObject(url, newFriendRequestDto, FriendRequestDto.class);

        sendFriendRequestNotification(senderId, recipientId);

        return friendRequestDto;
    }

    private void sendFriendRequestNotification(long senderId, long recipientId) {
        var sender = userService.getUser(senderId);
        var notification = NewNotificationDto.builder()
                .type("friend_request")
                .recipientId(recipientId)
                .senderId(senderId)
                .senderName(sender.getLogin())
                .pictureUrl(sender.getLogoUrl())
                .text("пользователь " + sender.getLogin() + " хочет добавить вас в друзья")
                .build();

        notificationService.addNotification(notification);
    }


    public void deleteAllFriendRequests() {
        String url = userServiceUrl + "/api/v1/friend_requests";
        restTemplate.delete(url);
    }

    @Autowired
    public void setUserServiceUrl(@Value("${endpoints.user-service-url}") String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
