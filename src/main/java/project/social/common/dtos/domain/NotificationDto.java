package project.social.common.dtos.domain;

import project.social.domain.Notification;
import project.social.domain.enums.NotificationType;

import java.time.OffsetDateTime;

public record NotificationDto(
        String id,
        String recipientId,
        NotificationType type,
        String sourceUserId,
        String postId,
        String commentId,
        OffsetDateTime createdAt,
        Boolean isRead
) {
    public NotificationDto(Notification notification) {
        this(
                notification.getId(),
                notification.getRecipientId(),
                notification.getType(),
                notification.getSourceUserId(),
                notification.getPostId(),
                notification.getCommentId(),
                notification.getCreatedAt(),
                notification.getIsRead()
        );
    }
}
