package com.epicwuxia.messageservice.service;

import com.epicwuxia.messageservice.domain.Message;
import com.epicwuxia.messageservice.repository.MessageRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // public Message sendMessage(String content, String authorId) {
    //     Message message = new Message();
    //     message.setContent(content);
    //     message.setAuthorId(authorId);
    //     return messageRepository.save(message);
    // }

    public List<Message> getMessagesByAuthorId(String authorId) {
        return messageRepository.findByAuthorId(authorId);
    }

    // public List<Message> getMessagesByPublisherId(String publisherId) {
    //     return messageRepository.findByPublisherId(publisherId);
    // }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
