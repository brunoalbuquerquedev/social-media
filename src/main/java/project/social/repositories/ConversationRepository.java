package project.social.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.social.domain.Conversation;

@Repository
public interface ConversationRepository extends MongoRepository<Conversation, String> {
    Page<Conversation> findByParticipantIdsContaining(String userId, Pageable pageable);
}