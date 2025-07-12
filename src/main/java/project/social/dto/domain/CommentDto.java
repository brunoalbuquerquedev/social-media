package project.social.dto.domain;

import project.social.domain.Comment;

import java.time.OffsetDateTime;

public record CommentDto(
        String id,
        String postId,
        String authorId,
        String content,
        OffsetDateTime createdAt
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
