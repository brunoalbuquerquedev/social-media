package project.social.dto.domain;

import jakarta.validation.constraints.NotNull;
import project.social.domain.Like;
import project.social.domain.Post;

import java.time.OffsetDateTime;
import java.util.List;

public record PostDto(
        @NotNull String id,
        @NotNull String authorId,
        @NotNull String authorUsername,
        @NotNull String content,
        @NotNull String authorProfilePictureUrl,
        @NotNull OffsetDateTime createdAt,
        @NotNull List<Like> likes,
        @NotNull List<CommentDto> comments,
        @NotNull Boolean hasUserLiked
) {
    public PostDto(Post post) {
        this(
                post.getId(),
                post.getAuthorId(),
                post.getAuthorUsername(),
                post.getContent(),
                post.getAuthorProfilePictureUrl(),
                post.getCreatedAt(),
                post.getLikes(),
                post.getComments(),
                post.isHasUserLiked()
        );
    }
}
