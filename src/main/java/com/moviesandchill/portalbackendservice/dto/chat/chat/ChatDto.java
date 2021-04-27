package com.moviesandchill.portalbackendservice.dto.chat.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatDto {
    private long chatId;

    private String name;
}
