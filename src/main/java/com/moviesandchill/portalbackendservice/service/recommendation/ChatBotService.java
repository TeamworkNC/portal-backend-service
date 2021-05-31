package com.moviesandchill.portalbackendservice.service.recommendation;

import com.moviesandchill.portalbackendservice.dto.recommendation.message.ChatBotMessageDto;
import com.moviesandchill.portalbackendservice.dto.recommendation.message.RecommendationChatBotMessageDto;
import com.moviesandchill.portalbackendservice.dto.recommendation.message.UserMessageDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatBotService {
    private final RestTemplate restTemplate;
    @Value("${endpoints.recommendation-service-url}")
    private String recommendationServiceUrl;

    public ChatBotService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ChatBotMessageDto answerToUser(UserMessageDto userMessageDto) {
        String url = recommendationServiceUrl + "/api/v1/chat-bot";
        var answer = restTemplate.postForObject(url, userMessageDto, RecommendationChatBotMessageDto.class);
        assert answer != null;
        if (answer.getFilms() == null) {
            return new ChatBotMessageDto(answer.getText());
        }
        return answer;
    }
}
