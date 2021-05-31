package com.moviesandchill.portalbackendservice.controller.chat;

import com.moviesandchill.portalbackendservice.dto.chat.message.MessageDto;
import com.moviesandchill.portalbackendservice.dto.chat.message.NewMessageDto;
import com.moviesandchill.portalbackendservice.service.chat.MessageService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/messages",
        produces = "application/json"
)
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping()
    private List<MessageDto> getAllMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping
    @Secured("ROLE_USER")
    MessageDto addMessage(@RequestBody NewMessageDto newMessageDto) {
        return messageService.addMessage(newMessageDto);
    }

    @DeleteMapping
    @Secured("ROLE_USER")
    void deleteAllMessages() {
        messageService.deleteAllMessages();
    }

    @GetMapping("/{id}")
    private MessageDto getMessageById(@PathVariable("id") long messageId) {
        return messageService.getMessageById(messageId);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_USER")
    private void deleteMessageById(@PathVariable("id") long messageId) {
        messageService.deleteMessageById(messageId);
    }
}
