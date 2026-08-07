package project.social.common.dtos.domain;

import project.social.domain.Comment;

import java.time.OffsetDateTime;

public record CommentDto(
        String id,
        String postId,
        String authorId,
        String authorUsername,
        String authorProfilePictureUrl,
        String content,
        OffsetDateTime createdAt,
        OffsetDateTime editedAt,
        OffsetDateTime updatedAt,
        Integer likesCount
) {
    public CommentDto(Comment comment) {
        this(
                comment.getId(),
                comment.getPostId(),
                comment.getAuthorId(),
                comment.getAuthorUsername(),
                comment.getAuthorProfilePictureUrl(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getEditedAt(),
                comment.getUpdatedAt(),
                comment.getLikesCount()
        );
    }
}
