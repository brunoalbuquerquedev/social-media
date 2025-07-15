package project.social.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.social.domain.Conversation;

import java.util.List;

public interface ConversationRepository extends MongoRepository<Conversation, String> {
    List<Conversation> findByParticipantsIdsContaining(String participantsId);
}