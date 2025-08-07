package project.social.common.dtos.domain;

import project.social.domain.Conversation;

import java.time.OffsetDateTime;
import java.util.List;

public record ConversationDto(
        String id,
        String lastMessageId,
        OffsetDateTime createdAt,
        List<String> participantsIds,
        List<String> messages
) {
    public ConversationDto(Conversation conversation) {
        this(
                conversation.getId(),
                conversation.getLastMessageId(),
                conversation.getCreatedAt(),
                conversation.getParticipantsIds(),
                conversation.getMessages()
        );
    }
}
