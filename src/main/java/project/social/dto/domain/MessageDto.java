package project.social.dto.domain;

import project.social.domain.Message;
import project.social.domain.enums.SeenStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public record MessageDto(
        String id,
        String conversationId,
        String senderId,
        String content,
        Date sentAt,
        SeenStatus seenStatus,
        boolean deletedBySender,
        boolean deletedByReceiver
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
