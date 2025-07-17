package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.domain.Conversation;
import project.social.domain.Message;
import project.social.domain.enums.SeenStatus;
import project.social.common.dtos.domain.ConversationDto;
import project.social.common.dtos.domain.MessageDto;
import project.social.common.exceptions.domain.MessageNotFoundException;
import project.social.common.exceptions.domain.NullValueInSeenStatusException;
import project.social.domain.mappers.MessageMapper;
import project.social.repositories.ConversationRepository;
import project.social.repositories.MessageRepository;
import project.social.services.interfaces.IChatService;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService implements IChatService {

    private ConversationRepository conversationRepository;
    private MessageRepository messageRepository;

    public Page<ConversationDto> getConversationsForUser(String userId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Conversation> page = conversationRepository.findByParticipantsIdsContaining(userId, pageable);
        return page.map(ConversationDto::new);
    }

    public Page<MessageDto> getMessages(String conversationId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Message> page = messageRepository.findByConversationIdOrderBySentAtAsc(conversationId, pageable);
        return page.map(MessageDto::new);
    }

    @Override
    public MessageDto sendMessage(String conversationId, String senderId, String content) {
        Message message = Message.builder()
                .conversationId(conversationId)
                .senderId(senderId)
                .content(content)
                .sentAt(OffsetDateTime.now())
                .seenStatus(SeenStatus.SENT)
                .build();

        Message savedMessage = messageRepository.save(message);
        conversationRepository.findById(conversationId).ifPresent(conversation -> {
            conversation.setLastMessageId(savedMessage.getId());
            conversationRepository.save(conversation);
        });
        return MessageMapper.toDto(savedMessage);
    }

    @Override
    public ConversationDto startConversation(List<String> participantIds) {
        Conversation conversation = Conversation.builder()
                .participantsIds(participantIds)
                .createdAt(OffsetDateTime.now())
                .build();
        Conversation savedConversation = conversationRepository.save(conversation);
        return new ConversationDto(savedConversation);
    }

    @Override
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
