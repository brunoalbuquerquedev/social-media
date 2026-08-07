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
                message.getIsRead(),
                message.getReadAt(),
                message.isDeletedBySender(),
                message.isDeletedByReceiver(),
                message.getDeletedAt()
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
                .isRead(dto.isRead())
                .readAt(dto.readAt())
                .deletedBySender(dto.deletedBySender())
                .deletedByReceiver(dto.deletedByReceiver())
                .deletedAt(dto.deletedAt())
                .build();
    }
}
