package project.social.dto.domain;

import project.social.domain.Conversation;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

public record ConversationDto(
        String id,
        String lastMessageId,
        OffsetDateTime createdAt,
        List<String> participantsIds,
        List<String> messages
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
