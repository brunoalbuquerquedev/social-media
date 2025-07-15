package project.social.dto.domain;

import jakarta.validation.constraints.NotNull;
import project.social.domain.Conversation;

import java.time.OffsetDateTime;
import java.util.List;

public record ConversationDto(
        @NotNull String id,
        @NotNull String lastMessageId,
        @NotNull OffsetDateTime createdAt,
        @NotNull List<String> participantsIds,
        @NotNull List<String> messages
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
