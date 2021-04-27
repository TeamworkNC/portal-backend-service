package com.moviesandchill.portalbackendservice.service.chat;


import com.moviesandchill.portalbackendservice.dto.chat.chat.ChatDto;
import com.moviesandchill.portalbackendservice.dto.chat.chat.NewChatDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ChatService {

    private final String chatServiceUrl;
    private final RestTemplate restTemplate;

    public ChatService(@Value("${endpoint.chat-service.url}") String chatServiceUrl, RestTemplate restTemplate) {
        this.chatServiceUrl = chatServiceUrl;
        this.restTemplate = restTemplate;
    }

    public List<ChatDto> getAllChats() {
        String url = chatServiceUrl + "/api/v1/chats";
        var dtos = restTemplate.getForObject(url, ChatDto[].class);
        return Arrays.asList(Objects.requireNonNull(dtos));
    }

    public ChatDto addChat(NewChatDto newChatDto) {
        String url = chatServiceUrl + "/api/v1/chats";
        return restTemplate.postForObject(url, newChatDto, ChatDto.class);
    }

    public void deleteAllChats() {
        String url = chatServiceUrl + "/api/v1/chats";
        restTemplate.delete(url);
    }

    public ChatDto getChatById(long chatId) {
        String url = chatServiceUrl + "/api/v1/chats/" + chatId;
        return restTemplate.getForObject(url, ChatDto.class);
    }

    public void deleteChatById(long chatId) {
        String url = chatServiceUrl + "/api/v1/chats/" + chatId;
        restTemplate.delete(url);
    }
}
