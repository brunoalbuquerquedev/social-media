package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.common.dtos.domain.ConversationDto;
import project.social.common.dtos.domain.MessageDto;

import java.util.List;

public interface IChatService {
    Page<ConversationDto> getConversationsForUser(String userId, int page, int size);

    Page<MessageDto> getMessages(String conversationId, int page, int size);

    MessageDto sendMessage(String conversationId, String senderId, String content);

    ConversationDto startConversation(List<String> participantIds);

    void markAsSeen(String messageId, String userId);
}
