package com.epicwuxia.messageservice.web.rest;

import com.epicwuxia.messageservice.domain.Message;
import com.epicwuxia.messageservice.service.MessageService;
import com.epicwuxia.messageservice.service.SubscriptionService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageResource {

    private final MessageService messageService;
    private final SubscriptionService subscriptionService;

    public MessageResource(MessageService messageService, SubscriptionService subscriptionService) {
        this.messageService = messageService;
        this.subscriptionService = subscriptionService;
    }

    // @PostMapping
    // public ResponseEntity<Message> sendMessage(@RequestParam String content,
    // @RequestParam String authorId) {
    // Message message = messageService.sendMessage(content, authorId);
    // return ResponseEntity.ok(message);
    // }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Message>> getMessagesByAuthorId(@PathVariable String authorId) {
        List<Message> messages = messageService.getMessagesByAuthorId(authorId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/subscriber/{subscriberId}")
    public ResponseEntity<List<Message>> getMessagesForSubscriber(@RequestParam String subscriberId) {
        List<Message> messages = subscriptionService.getMessages(subscriberId);
        return ResponseEntity.ok(messages);
    }
}
