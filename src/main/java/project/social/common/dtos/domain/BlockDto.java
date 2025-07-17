package project.social.common.dtos.domain;

import project.social.domain.Block;
import project.social.domain.enums.RestrictionType;

import java.time.OffsetDateTime;

public record BlockDto(
        String id,
        String blockerUserId,
        String blockingUserId,
        OffsetDateTime createdAt,
        RestrictionType type
) {
    public BlockDto(Block block) {
        this(
                block.getId(),
                block.getBlockerUserId(),
                block.getBlockingUserId(),
                block.getCreatedAt(),
                block.getType()
        );
    }
}
