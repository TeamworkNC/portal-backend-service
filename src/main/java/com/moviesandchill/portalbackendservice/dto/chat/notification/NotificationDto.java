package com.moviesandchill.portalbackendservice.dto.chat.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDto {

    private long notificationId;

    private long recipientId;

    private long senderId;

    private String senderName;

    private String text;

    private String pictureUrl;

    private String type;

    private String roomUrl;

    private String filmTitle;
}
