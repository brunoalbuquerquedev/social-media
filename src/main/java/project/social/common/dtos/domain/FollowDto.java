package project.social.common.dtos.domain;

import project.social.domain.Follow;
import project.social.domain.enums.FollowStatus;

import java.time.OffsetDateTime;

public record FollowDto(
        String id,
        String followerUserId,
        String followingUserId,
        OffsetDateTime followedAt,
        Boolean notified,
        FollowStatus status
) {
    public FollowDto(Follow follow) {
        this(
                follow.getId(),
                follow.getFollowerUserId(),
                follow.getFollowingUserId(),
                follow.getFollowedAt(),
                follow.isNotified(),
                follow.getStatus()
        );
    }
}
