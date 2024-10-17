package com.epicwuxia.messageservice.repository;

import com.epicwuxia.messageservice.domain.Message;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByAuthorId(String authorId);

    // List<Message> findByPublisherId(String publisherId);
    List<Message> findByAuthorIdIn(List<String> authorIds);
}
