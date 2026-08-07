package project.social.common.dtos.domain;

import project.social.domain.Message;
import project.social.domain.enums.SeenStatus;

import java.time.OffsetDateTime;

public record MessageDto(
        String id,
        String conversationId,
        String senderId,
        String content,
        OffsetDateTime sentAt,
        SeenStatus seenStatus,
        Boolean isRead,
        OffsetDateTime readAt,
        Boolean deletedBySender,
        Boolean deletedByReceiver,
        OffsetDateTime deletedAt
) {
    public MessageDto(Message message) {
        this(
                message.getId(),
                message.getConversationId(),
                message.getSenderId(),
                message.getContent(),
                message.getSentAt(),
                message.getSeenStatus(),
                message.getIsRead(),
                message.getReadAt(),
                message.isDeletedBySender(),
                message.isDeletedByReceiver(),
                message.getDeletedAt()
        );
    }
}
