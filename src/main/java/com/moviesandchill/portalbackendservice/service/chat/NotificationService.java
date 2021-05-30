package com.moviesandchill.portalbackendservice.service.chat;

import com.moviesandchill.portalbackendservice.dto.chat.notification.NewNotificationDto;
import com.moviesandchill.portalbackendservice.dto.chat.notification.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class NotificationService {

    private final String chatServiceUrl;
    private final RestTemplate restTemplate;

    public NotificationService(@Value("${endpoints.chat-service-url}") String chatServiceUrl, RestTemplate restTemplate) {
        this.chatServiceUrl = chatServiceUrl;
        this.restTemplate = restTemplate;
    }

    public List<NotificationDto> getAllNotifications() {
        String url = chatServiceUrl + "/api/v1/notifications";
        var dtos = restTemplate.getForObject(url, NotificationDto[].class);
        return Arrays.asList(Objects.requireNonNull(dtos));
    }

    public NotificationDto addNotification(NewNotificationDto newNotificationDto) {
        String url = chatServiceUrl + "/api/v1/notifications";
        return restTemplate.postForObject(url, newNotificationDto, NotificationDto.class);
    }

    public void deleteAllNotifications() {
        String url = chatServiceUrl + "/api/v1/notifications";
        restTemplate.delete(url);
    }

    public List<NotificationDto> getNotificationsByUserId(long userId) {
        String url = chatServiceUrl + "/api/v1/notifications?user_id=" + userId;
        var dtos = restTemplate.getForObject(url, NotificationDto[].class);
        return Arrays.asList(Objects.requireNonNull(dtos));
    }

    public void deleteNotificationsByUserId(long userId) {
        String url = chatServiceUrl + "/api/v1/notifications?user_id=" + userId;
        restTemplate.delete(url);
    }

    public void deleteNotification(long notificationId) {
        String url = chatServiceUrl + "/api/v1/notifications/" + notificationId;
        restTemplate.delete(url);
    }
}
