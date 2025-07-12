package project.social.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import project.social.domain.enums.FollowStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "follows")
public class Follow implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String followerUserId;
    private String followingUserId;
    private OffsetDateTime createdAt;
    private boolean notified;
    private FollowStatus status;
}
