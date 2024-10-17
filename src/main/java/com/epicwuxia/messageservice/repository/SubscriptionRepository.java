// MessageService - src/main/java/com/epicwuxia/messageservice/repository/SubscriptionRepository.java
package com.epicwuxia.messageservice.repository;

import com.epicwuxia.messageservice.domain.Subscription;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
    List<Subscription> findByProducerId(String producerId);

    List<Subscription> findBySubscriberId(String SubscriberId);
}
