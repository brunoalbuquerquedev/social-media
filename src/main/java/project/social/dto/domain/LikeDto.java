package project.social.dto.domain;

import jakarta.validation.constraints.NotNull;
import project.social.domain.Like;

import java.time.OffsetDateTime;

public record LikeDto(
        @NotNull String id,
        @NotNull String userId,
        @NotNull String postId,
        @NotNull OffsetDateTime likedAt
) {

    public LikeDto(Like like) {
        this(
                like.getId(),
                like.getUserId(),
                like.getPostId(),
                like.getLikedAt()
        );
    }
}
