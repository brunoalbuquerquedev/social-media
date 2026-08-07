package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.common.dtos.domain.NotificationDto;
import project.social.common.exceptions.base.ObjectNotFoundException;
import project.social.domain.Notification;
import project.social.domain.enums.NotificationType;
import project.social.repositories.NotificationRepository;
import project.social.services.interfaces.INotificationService;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public NotificationDto createNotification(String recipientId, NotificationType type, 
                                              String sourceUserId, String postId, String commentId) {
        Notification notification = Notification.builder()
                .recipientId(recipientId)
                .type(type)
                .sourceUserId(sourceUserId)
                .postId(postId)
                .commentId(commentId)
                .createdAt(OffsetDateTime.now())
                .isRead(false)
                .build();

        Notification saved = notificationRepository.save(notification);
        return new NotificationDto(saved);
    }

    @Override
    public Page<NotificationDto> getNotifications(String recipientId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return notificationRepository.findByRecipientIdOrderByCreatedAtDesc(recipientId, pageable)
                .map(NotificationDto::new);
    }

    @Override
    public void markAsRead(String id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Notification not found."));
        
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }

    @Override
    public void markAllAsRead(String recipientId) {
        notificationRepository.findByRecipientIdAndIsRead(recipientId, false)
                .forEach(notification -> {
                    notification.setIsRead(true);
                    notificationRepository.save(notification);
                });
    }

    @Override
    public long getUnreadCount(String recipientId) {
        return notificationRepository.countByRecipientIdAndIsRead(recipientId, false);
    }

    @Override
    public NotificationDto getNotificationById(String id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Notification not found."));
        return new NotificationDto(notification);
    }

    @Override
    public void deleteNotification(String id) {
        if (!notificationRepository.existsById(id)) {
            throw new ObjectNotFoundException("Notification not found.");
        }
        notificationRepository.deleteById(id);
    }
}
