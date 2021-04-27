package com.moviesandchill.portalbackendservice.dto.chat.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDto {
    private long notificationId;

    private long userId;

    private LocalDateTime createdAt;

    private String text;
}
