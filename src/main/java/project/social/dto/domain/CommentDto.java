package project.social.dto.domain;

import jakarta.validation.constraints.NotNull;
import project.social.domain.Comment;

import java.time.OffsetDateTime;

public record CommentDto(
        @NotNull String id,
        @NotNull String postId,
        @NotNull String authorId,
        @NotNull String content,
        @NotNull OffsetDateTime createdAt
) {
    public CommentDto(Comment comment) {
        this(
                comment.getId(),
                comment.getPostId(),
                comment.getAuthorId(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }
}
