package project.social.common.dtos.domain;

import project.social.domain.Hashtag;

import java.time.OffsetDateTime;

public record HashtagDto(
        String id,
        String tag,
        Integer usageCount,
        OffsetDateTime lastUsedAt
) {
    public HashtagDto(Hashtag hashtag) {
        this(
                hashtag.getId(),
                hashtag.getTag(),
                hashtag.getUsageCount(),
                hashtag.getLastUsedAt()
        );
    }
}
