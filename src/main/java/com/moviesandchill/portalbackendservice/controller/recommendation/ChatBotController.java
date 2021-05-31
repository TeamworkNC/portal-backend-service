package com.moviesandchill.portalbackendservice.controller.recommendation;

import com.moviesandchill.portalbackendservice.dto.recommendation.message.ChatBotMessageDto;
import com.moviesandchill.portalbackendservice.dto.recommendation.message.UserMessageDto;
import com.moviesandchill.portalbackendservice.service.recommendation.ChatBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/chat-bot")
@Slf4j
public class ChatBotController {
    private final ChatBotService chatBotService;

    public ChatBotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @PostMapping
    @Secured("ROLE_USER")
    public ChatBotMessageDto answer(@RequestBody UserMessageDto userMessageDto) {
        return chatBotService.answerToUser(userMessageDto);
    }
}
