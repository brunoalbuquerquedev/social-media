package project.social.dto.domain;

import jakarta.validation.constraints.NotNull;
import project.social.domain.Follow;
import project.social.domain.enums.FollowStatus;

import java.time.OffsetDateTime;

public record FollowDto(
        @NotNull String id,
        @NotNull String followerUserId,
        @NotNull String followingUserId,
        @NotNull OffsetDateTime followedAt,
        @NotNull Boolean notified,
        @NotNull FollowStatus status
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
