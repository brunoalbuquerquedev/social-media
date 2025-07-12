package project.social.services.interfaces;

import project.social.dto.domain.ConversationDto;
import project.social.dto.domain.MessageDto;

import java.util.List;

public interface IChatService {
    List<ConversationDto> getConversationsForUser(String userId);

    List<MessageDto> getMessages(String conversationId);

    MessageDto sendMessage(String conversationId, String senderId, String content);

    ConversationDto startConversation(List<String> participantIds, boolean isGroup);

    void markAsSeen(String messageId, String userId);
}
