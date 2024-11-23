package org.cst8277.GanWei.messageservice.service;

import java.time.LocalDateTime;
import java.util.List;
import org.cst8277.GanWei.messageservice.domain.Message;
import org.cst8277.GanWei.messageservice.repository.MessageRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByAuthorId(String authorId) {
        return messageRepository.findByAuthorId(authorId);
    }

    // public List<Message> getMessagesByPublisherId(String publisherId) {
    // return messageRepository.findByPublisherId(publisherId);
    // }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public List<Message> searchMessages(String keyword) {
        return messageRepository.findByContentContaining(keyword);
    }
}
