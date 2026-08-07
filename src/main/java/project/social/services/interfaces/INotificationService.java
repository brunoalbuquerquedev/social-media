package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.common.dtos.domain.NotificationDto;
import project.social.domain.enums.NotificationType;

public interface INotificationService {
    /**
     * Creates a new notification.
     *
     * @param recipientId the ID of the recipient
     * @param type the type of notification
     * @param sourceUserId the ID of the source user
     * @param postId the ID of the post (optional)
     * @param commentId the ID of the comment (optional)
     * @return the created NotificationDto
     */
    NotificationDto createNotification(String recipientId, NotificationType type, 
                                       String sourceUserId, String postId, String commentId);

    /**
     * Retrieves notifications for a user.
     *
     * @param recipientId the ID of the recipient
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of NotificationDtos
     */
    Page<NotificationDto> getNotifications(String recipientId, int pageNumber, int pageSize);

    /**
     * Marks a notification as read.
     *
     * @param id the ID of the notification
     */
    void markAsRead(String id);

    /**
     * Marks all notifications as read for a user.
     *
     * @param recipientId the ID of the recipient
     */
    void markAllAsRead(String recipientId);

    /**
     * Gets the count of unread notifications for a user.
     *
     * @param recipientId the ID of the recipient
     * @return the count of unread notifications
     */
    long getUnreadCount(String recipientId);

    /**
     * Retrieves a notification by its ID.
     *
     * @param id the ID of the notification
     * @return the NotificationDto if found
     */
    NotificationDto getNotificationById(String id);

    /**
     * Deletes a notification.
     *
     * @param id the ID of the notification
     */
    void deleteNotification(String id);
}
