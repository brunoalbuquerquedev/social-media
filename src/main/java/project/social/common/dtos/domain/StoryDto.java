package project.social.common.dtos.domain;

import project.social.domain.Story;

import java.time.OffsetDateTime;

public record StoryDto(
        String id,
        String authorId,
        String content,
        String mediaUrl,
        OffsetDateTime createdAt,
        OffsetDateTime expiresAt
) {
    public StoryDto(Story story) {
        this(
                story.getId(),
                story.getAuthorId(),
                story.getContent(),
                story.getMediaUrl(),
                story.getCreatedAt(),
                story.getExpiresAt()
        );
    }
}
