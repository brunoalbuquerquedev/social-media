package project.social.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import project.social.domain.enums.SeenStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "messages")
public class Message implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String conversationId;
    private String senderId;
    private String content;
    private OffsetDateTime sentAt;

    @Builder.Default
    private SeenStatus seenStatus = SeenStatus.PENDING;

    private boolean deletedBySender;
    private boolean deletedByReceiver;
}
