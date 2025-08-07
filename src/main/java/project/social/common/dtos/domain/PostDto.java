package project.social.common.dtos.domain;

import project.social.domain.Like;
import project.social.domain.Post;

import java.time.OffsetDateTime;
import java.util.List;

public record PostDto(
        String id,
        String authorId,
        String authorUsername,
        String content,
        String authorProfilePictureUrl,
        OffsetDateTime createdAt,
        List<Like> likes,
        List<CommentDto> comments,
        Boolean hasUserLiked
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
