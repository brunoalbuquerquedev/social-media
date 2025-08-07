package project.social.domain.mappers;

import project.social.domain.Message;
import project.social.common.dtos.domain.MessageDto;

public class MessageMapper {

    public static MessageDto toDto(Message message) {
        if (message == null) return null;

        return new MessageDto(
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

    public static Message fromDto(MessageDto dto) {
        if (dto == null) return null;

        return Message.builder()
                .id(dto.id())
                .conversationId(dto.conversationId())
                .senderId(dto.senderId())
                .content(dto.content())
                .sentAt(dto.sentAt())
                .seenStatus(dto.seenStatus())
                .deletedBySender(dto.deletedBySender())
                .deletedByReceiver(dto.deletedByReceiver())
                .build();
    }
}
