package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.common.annotations.CurrentUser;
import project.social.common.dtos.domain.NotificationDto;
import project.social.services.interfaces.INotificationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationResource {

    private final INotificationService notificationService;

    /**
     * Retrieves notifications for the current user.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param currentUserId the ID of the current user
     * @return a paginated list of NotificationDtos
     */
    @GetMapping
    public ResponseEntity<Page<NotificationDto>> getNotifications(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @CurrentUser String currentUserId) {
        Page<NotificationDto> notifications = notificationService.getNotifications(currentUserId, pageNumber, pageSize);
        return ResponseEntity.ok(notifications);
    }

    /**
     * Gets the count of unread notifications.
     *
     * @param currentUserId the ID of the current user
     * @return the count of unread notifications
     */
    @GetMapping("/unread/count")
    public ResponseEntity<Long> getUnreadCount(@CurrentUser String currentUserId) {
        long count = notificationService.getUnreadCount(currentUserId);
        return ResponseEntity.ok(count);
    }

    /**
     * Marks a notification as read.
     *
     * @param id the ID of the notification
     * @param currentUserId the ID of the current user
     * @return no content response
     */
    @PutMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(
            @PathVariable String id,
            @CurrentUser String currentUserId) {
        notificationService.markAsRead(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Marks all notifications as read.
     *
     * @param currentUserId the ID of the current user
     * @return no content response
     */
    @PutMapping("/all/read")
    public ResponseEntity<Void> markAllAsRead(@CurrentUser String currentUserId) {
        notificationService.markAllAsRead(currentUserId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a notification.
     *
     * @param id the ID of the notification
     * @param currentUserId the ID of the current user
     * @return no content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(
            @PathVariable String id,
            @CurrentUser String currentUserId) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
