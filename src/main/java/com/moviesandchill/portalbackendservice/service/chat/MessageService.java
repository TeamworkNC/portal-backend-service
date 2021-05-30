package com.moviesandchill.portalbackendservice.service.chat;

import com.moviesandchill.portalbackendservice.dto.chat.message.MessageDto;
import com.moviesandchill.portalbackendservice.dto.chat.message.NewMessageDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class MessageService {

    private final String chatServiceUrl;
    private final RestTemplate restTemplate;

    public MessageService(@Value("${endpoints.chat-service-url}") String chatServiceUrl, RestTemplate restTemplate) {
        this.chatServiceUrl = chatServiceUrl;
        this.restTemplate = restTemplate;
    }

    public List<MessageDto> getAllMessages() {
        String url = chatServiceUrl + "/api/v1/messages";
        var dtos = restTemplate.getForObject(url, MessageDto[].class);
        return Arrays.asList(Objects.requireNonNull(dtos));
    }

    public MessageDto addMessage(NewMessageDto newMessageDto) {
        String url = chatServiceUrl + "/api/v1/messages";
        return restTemplate.postForObject(url, newMessageDto, MessageDto.class);
    }

    public void deleteAllMessages() {
        String url = chatServiceUrl + "/api/v1/messages";
        restTemplate.delete(url);
    }

    public List<MessageDto> getAllMessagesByChatId(long chatId) {
        String url = chatServiceUrl + "/api/v1/chats/" + chatId + "/messages";
        var dtos = restTemplate.getForObject(url, MessageDto[].class);
        return Arrays.asList(Objects.requireNonNull(dtos));
    }

    public void deleteAllMessagesByChatId(long chatId) {
        String url = chatServiceUrl + "/api/v1/chats/" + chatId + "/messages";
        restTemplate.delete(url);
    }

    public MessageDto getMessageById(long messageId) {
        String url = chatServiceUrl + "/api/v1/messages/" + messageId;
        return restTemplate.getForObject(url, MessageDto.class);
    }

    public void deleteMessageById(long messageId) {
        String url = chatServiceUrl + "/api/v1/messages/" + messageId;
        restTemplate.delete(url);
    }
}
