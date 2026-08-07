package project.social.common.dtos.domain;

import project.social.domain.Bookmark;

import java.time.OffsetDateTime;

public record BookmarkDto(
        String id,
        String postId,
        String userId,
        OffsetDateTime createdAt
) {
    public BookmarkDto(Bookmark bookmark) {
        this(
                bookmark.getId(),
                bookmark.getPostId(),
                bookmark.getUserId(),
                bookmark.getCreatedAt()
        );
    }
}
