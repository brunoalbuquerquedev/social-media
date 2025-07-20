package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.dto.domain.ConversationDto;
import project.social.dto.domain.MessageDto;

import java.util.List;

public interface IChatService {
    /**
     * Retrieves conversations for a specific user.
     *
     * @param userId the ID of the user
     * @param page the page number to retrieve
     * @param size the number of items per page
     * @return a paginated list of ConversationDto objects
     */
    Page<ConversationDto> getConversationsForUser(String userId, int page, int size);

    /**
     * Retrieves messages for a specific conversation.
     *
     * @param conversationId the ID of the conversation
     * @param page the page number to retrieve
     * @param size the number of items per page
     * @return a paginated list of MessageDto objects
     */
    Page<MessageDto> getMessages(String conversationId, int page, int size);

    /**
     * Sends a message in a conversation.
     *
     * @param conversationId the ID of the conversation
     * @param senderId the ID of the sender
     * @param content the content of the message
     * @return the sent MessageDto
     */
    MessageDto sendMessage(String conversationId, String senderId, String content);

    /**
     * Starts a new conversation with the specified participants.
     *
     * @param participantIds the IDs of the participants
     * @return the created ConversationDto
     */
    ConversationDto startConversation(List<String> participantIds);

    /**
     * Marks a message as seen by a user.
     *
     * @param messageId the ID of the message
     * @param userId the ID of the user who has seen the message
     */
    void markAsSeen(String messageId, String userId);
}
