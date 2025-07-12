package project.social.dto.domain;

import project.social.domain.Comment;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

public record CommentDto(
        String id,
        String postId,
        String authorId,
        String content,
        OffsetDateTime createdAt
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
