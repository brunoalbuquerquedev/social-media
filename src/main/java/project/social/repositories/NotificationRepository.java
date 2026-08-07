package project.social.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.social.domain.Notification;
import project.social.domain.enums.NotificationType;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    Page<Notification> findByRecipientId(String recipientId, Pageable pageable);

    Page<Notification> findByRecipientIdOrderByCreatedAtDesc(String recipientId, Pageable pageable);

    long countByRecipientIdAndIsRead(String recipientId, Boolean isRead);

    void updateByRecipientIdAndIsRead(String recipientId, Boolean isRead);

    List<Notification> findByRecipientIdAndIsRead(String recipientId, Boolean isRead);

    List<Notification> findByRecipientIdAndType(String recipientId, NotificationType type);
}
