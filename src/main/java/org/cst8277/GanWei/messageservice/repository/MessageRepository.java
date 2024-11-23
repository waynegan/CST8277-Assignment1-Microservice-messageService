package org.cst8277.GanWei.messageservice.repository;

import java.util.List;
import org.cst8277.GanWei.messageservice.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByAuthorId(String authorId);

    // List<Message> findByPublisherId(String publisherId);
    List<Message> findByAuthorIdIn(List<String> authorIds);

    List<Message> findByContentContaining(String keyword);
}
