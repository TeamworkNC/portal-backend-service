package com.moviesandchill.portalbackendservice.dto.chat.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewNotificationDto {
    private long userId;

    private String text;
}
