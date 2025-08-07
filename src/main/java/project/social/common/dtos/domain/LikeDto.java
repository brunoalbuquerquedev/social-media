package project.social.common.dtos.domain;

import project.social.domain.Like;

import java.time.OffsetDateTime;

public record LikeDto(
        String id,
        String userId,
        String postId,
        OffsetDateTime likedAt
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
