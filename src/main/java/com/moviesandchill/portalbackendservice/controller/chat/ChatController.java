package com.moviesandchill.portalbackendservice.controller.chat;

import com.moviesandchill.portalbackendservice.dto.chat.chat.ChatDto;
import com.moviesandchill.portalbackendservice.dto.chat.chat.NewChatDto;
import com.moviesandchill.portalbackendservice.dto.chat.message.MessageDto;
import com.moviesandchill.portalbackendservice.service.chat.ChatService;
import com.moviesandchill.portalbackendservice.service.chat.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "api/v1/chats",
        produces = "application/json"
)
public class ChatController {

    private ChatService chatService;
    private MessageService messageService;

    @GetMapping
    public List<ChatDto> getAllChats() {
        return chatService.getAllChats();
    }

    @PostMapping
    @Secured("ROLE_USER")
    public ChatDto addChat(@RequestBody NewChatDto newChatDto) {
        return chatService.addChat(newChatDto);
    }

    @DeleteMapping
    @Secured("ROLE_USER")
    public void deleteAllChats() {
        chatService.deleteAllChats();
    }

    @GetMapping("/{id}")
    public ChatDto getChatById(@PathVariable("id") long chatId) {
        return chatService.getChatById(chatId);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_USER")
    public void deleteChatById(@PathVariable("id") long chatId) {
        chatService.deleteChatById(chatId);
    }

    @GetMapping("/{id}/messages")
    List<MessageDto> getAllMessagesByChatId(@PathVariable("id") long chatId) {
        return messageService.getAllMessagesByChatId(chatId);
    }

    @DeleteMapping("/{id}/messages")
    @Secured("ROLE_USER")
    void deleteAllMessagesByChatId(@PathVariable("id") long chatId) {
        messageService.deleteAllMessagesByChatId(chatId);
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}
