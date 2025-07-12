package project.social.dto.domain;

import project.social.domain.Follow;
import project.social.domain.enums.FollowStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

public record FollowDto(
        String id,
        String followerUserId,
        String followingUserId,
        OffsetDateTime createdAt,
        boolean notified,
        FollowStatus status
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public FollowDto(Follow follow) {
        this(
                follow.getId(),
                follow.getFollowerUserId(),
                follow.getFollowingUserId(),
                follow.getCreatedAt(),
                follow.isNotified(),
                follow.getStatus()
        );
    }
}
