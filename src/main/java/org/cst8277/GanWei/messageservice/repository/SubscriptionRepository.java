// MessageService - src/main/java/com/epicwuxia/messageservice/repository/SubscriptionRepository.java
package org.cst8277.GanWei.messageservice.repository;

import java.util.List;
import org.cst8277.GanWei.messageservice.domain.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
    List<Subscription> findByProducerId(String producerId);

    List<Subscription> findBySubscriberId(String SubscriberId);
}
