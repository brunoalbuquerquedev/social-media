package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.social.domain.Conversation;
import project.social.domain.Message;
import project.social.domain.enums.SeenStatus;
import project.social.dto.domain.ConversationDto;
import project.social.dto.domain.MessageDto;
import project.social.exceptions.domain.MessageNotFoundException;
import project.social.exceptions.domain.NullValueInSeenStatusException;
import project.social.mappers.MessageMapper;
import project.social.repositories.ConversationRepository;
import project.social.repositories.MessageRepository;
import project.social.services.interfaces.IChatService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService implements IChatService {

    private ConversationRepository conversationRepository;
    private MessageRepository messageRepository;

    public List<ConversationDto> getConversationsForUser(String userId) {
        List<Conversation> list = conversationRepository.findByParticipantIdsContaining(userId);
        return list.stream().map(ConversationDto::new).toList();
    }

    public List<MessageDto> getMessages(String conversationId) {
        List<Message> list = messageRepository.findByConversationIdOrderBySentAtAsc(conversationId);
        return list.stream().map(MessageDto::new).toList();
    }

    public MessageDto sendMessage(String conversationId, String senderId, String content) {
        Message message = Message.builder()
                .conversationId(conversationId)
                .senderId(senderId)
                .content(content)
                .sentAt(new Date())
                .seenStatus(SeenStatus.SENT)
                .build();

        Message savedMessage = messageRepository.save(message);
        conversationRepository.findById(conversationId).ifPresent(conversation -> {
            conversation.setLastMessageId(savedMessage.getId());
            conversationRepository.save(conversation);
        });
        return MessageMapper.toDto(savedMessage);
    }

    public ConversationDto startConversation(List<String> participantIds, boolean isGroup) {
        Conversation conversation = Conversation.builder()
                .participantsIds(participantIds)
                .createdAt(new Date())
                .build();
        Conversation savedConversation = conversationRepository.save(conversation);
        return new ConversationDto(savedConversation);
    }

    public void markAsSeen(String messageId, String userId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new MessageNotFoundException("Message not found"));

        if (message.getSeenStatus() == null)
            throw new NullValueInSeenStatusException("Message does not have a seen status.");

        if (message.getSeenStatus() == SeenStatus.DELIVERED)
            message.setSeenStatus(SeenStatus.SEEN);

        messageRepository.save(message);
    }
}
