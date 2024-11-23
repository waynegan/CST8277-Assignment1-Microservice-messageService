package org.cst8277.GanWei.messageservice.web.rest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.logging.Logger;
import org.cst8277.GanWei.messageservice.domain.Message;
import org.cst8277.GanWei.messageservice.repository.MessageRepository;
import org.cst8277.GanWei.messageservice.repository.SubscriptionRepository;
import org.cst8277.GanWei.messageservice.service.MessageService;
import org.cst8277.GanWei.messageservice.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageResource {

    private final MessageService messageService;
    private final SubscriptionService subscriptionService;
    private final MessageRepository messageRepository;

    public MessageResource(MessageService messageService, SubscriptionService subscriptionService, MessageRepository messageRepository) {
        this.messageService = messageService;
        this.subscriptionService = subscriptionService;
        this.messageRepository = messageRepository;
    }

    @PreAuthorize("hasAuthority('ROLE_PRODUCER')") // Only users with the ROLE_PRODUCER authority can access this
    // endpoint
    @PostMapping
    public Message postMessage(@AuthenticationPrincipal Jwt jwt, @RequestBody Message message) {
        // Get the user ID from the JWT token
        String userId = jwt.getSubject();

        // Set the authorId to the authenticated user's ID
        message.setAuthorId(userId);
        message.setContent(message.getContent());
        // Set the createdAt timestamp
        message.setCreatedAt(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

        // Save the message using the service
        return messageService.saveMessage(message);
    }

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

    @GetMapping("/search")
    public ResponseEntity<List<Message>> searchMessages(@RequestParam String keyword) {
        List<Message> messages = messageService.searchMessages(keyword);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/mine")
    public List<Message> getMyMessages(@AuthenticationPrincipal Jwt jwt) {
        Logger logger = Logger.getLogger("MessageResource");
        logger.info("Received request to get messages for user: " + jwt.getSubject());
        // Extract the user ID (e.g., "sub" claim) from the JWT token
        String userId = jwt.getSubject();
        return subscriptionService.getMessages(userId);
        // return messageRepository.findByAuthorId(userId);
    }
}
