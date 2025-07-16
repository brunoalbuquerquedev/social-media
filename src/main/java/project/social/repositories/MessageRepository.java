package project.social.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.social.domain.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    Page<Message> findByConversationIdOrderBySentAtAsc(String conversationId, Pageable pageable);
}
