package project.social.dto.domain;

import jakarta.validation.constraints.NotNull;
import project.social.domain.Message;
import project.social.domain.enums.SeenStatus;

import java.time.OffsetDateTime;

public record MessageDto(
        @NotNull String id,
        @NotNull String conversationId,
        @NotNull String senderId,
        @NotNull String content,
        @NotNull OffsetDateTime sentAt,
        @NotNull SeenStatus seenStatus,
        @NotNull Boolean deletedBySender,
        @NotNull Boolean deletedByReceiver
) {
    public MessageDto(Message message) {
        this(
                message.getId(),
                message.getConversationId(),
                message.getSenderId(),
                message.getContent(),
                message.getSentAt(),
                message.getSeenStatus(),
                message.isDeletedBySender(),
                message.isDeletedByReceiver()
        );
    }
}
