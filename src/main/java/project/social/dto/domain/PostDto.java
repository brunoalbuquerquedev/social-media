package project.social.dto.domain;

import project.social.domain.Like;
import project.social.domain.Post;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

public record PostDto(
        String id,
        String authorId,
        String content,
        String title,
        OffsetDateTime createdAt,
        String authorUsername,
        String authorProfilePictureUrl,
        List<Like> likes,
        List<CommentDto> comments,
        boolean hasUserLiked
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public PostDto(Post post) {
        this(
                post.getId(),
                post.getAuthorId(),
                post.getContent(),
                post.getTitle(),
                post.getCreatedAt(),
                post.getAuthorUsername(),
                post.getAuthorProfilePictureUrl(),
                post.getLikes(),
                post.getComments(),
                post.isHasUserLiked()
        );
    }
}
