package project.social.common.dtos.domain;

import project.social.domain.Retweet;

import java.time.OffsetDateTime;

public record RetweetDto(
        String id,
        String postId,
        String userId,
        OffsetDateTime createdAt
) {
    public RetweetDto(Retweet retweet) {
        this(
                retweet.getId(),
                retweet.getPostId(),
                retweet.getUserId(),
                retweet.getCreatedAt()
        );
    }
}
