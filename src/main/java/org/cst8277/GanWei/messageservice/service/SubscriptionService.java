package org.cst8277.GanWei.messageservice.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.cst8277.GanWei.messageservice.domain.Message;
import org.cst8277.GanWei.messageservice.domain.Subscription;
import org.cst8277.GanWei.messageservice.repository.MessageRepository;
import org.cst8277.GanWei.messageservice.repository.SubscriptionRepository;
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
