package project.social.dto.domain;

import jakarta.validation.constraints.NotNull;
import project.social.domain.Block;
import project.social.domain.enums.RestrictionType;

import java.time.OffsetDateTime;

public record BlockDto(
        @NotNull String id,
        @NotNull String blockerUserId,
        @NotNull String blockingUserId,
        @NotNull OffsetDateTime createdAt,
        @NotNull RestrictionType type
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
