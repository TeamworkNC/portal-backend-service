package com.moviesandchill.portalbackendservice.controller.chat;

import com.moviesandchill.portalbackendservice.dto.chat.notification.NewNotificationDto;
import com.moviesandchill.portalbackendservice.dto.chat.notification.NotificationDto;
import com.moviesandchill.portalbackendservice.service.chat.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/notifications",
        produces = "application/json"
)
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @GetMapping
    private List<NotificationDto> getNotifications(@RequestParam(name = "user_id", required = false) Long userId) {
        if (userId != null) {
            return notificationService.getNotificationsByUserId(userId);
        }
        return notificationService.getAllNotifications();
    }

    @PostMapping
    private NotificationDto addNotification(@RequestBody NewNotificationDto newNotificationDto) {
        return notificationService.addNotification(newNotificationDto);
    }

    @DeleteMapping
    private void deleteNotifications(@RequestParam(name = "user_id", required = false) Long userId) {
        if (userId != null) {
            notificationService.deleteNotificationsByUserId(userId);
            return;
        }
        notificationService.deleteAllNotifications();
    }

    @DeleteMapping("/{notificationId}")
    private void deleteNotification(@PathVariable long notificationId) {
        notificationService.deleteNotification(notificationId);
    }
}
