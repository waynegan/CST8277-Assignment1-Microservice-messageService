package org.cst8277.GanWei.messageservice.web;

import java.util.List;
import org.cst8277.GanWei.messageservice.domain.Subscription;
import org.cst8277.GanWei.messageservice.repository.SubscriptionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionResource {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionResource(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @GetMapping("/by-producer")
    public List<Subscription> getSubscribersByProducerId(@RequestParam("producerId") String producerId) {
        return subscriptionRepository.findByProducerId(producerId);
    }
}
