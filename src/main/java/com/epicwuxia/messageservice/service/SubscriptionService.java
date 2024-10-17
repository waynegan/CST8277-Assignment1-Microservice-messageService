package com.epicwuxia.messageservice.service;

import com.epicwuxia.messageservice.domain.Message;
import com.epicwuxia.messageservice.domain.Subscription;
import com.epicwuxia.messageservice.repository.MessageRepository;
import com.epicwuxia.messageservice.repository.SubscriptionRepository;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    private SubscriptionRepository subscriptionRepository;
    private MessageRepository messageRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, MessageRepository messageRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages(String subscriberId) {
        List<Subscription> subscriptions = subscriptionRepository.findBySubscriberId(subscriberId);
        List<String> producerIds = subscriptions.stream().map(Subscription::getProducerId).collect(Collectors.toList());
        return messageRepository.findByAuthorIdIn(producerIds);
    }
}
