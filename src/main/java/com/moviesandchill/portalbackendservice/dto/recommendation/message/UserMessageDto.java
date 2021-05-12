package com.moviesandchill.portalbackendservice.dto.recommendation.message;

import lombok.Data;

@Data
public class UserMessageDto {
    private String text;
    private long userId;
}
