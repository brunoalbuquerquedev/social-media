package project.social.dto.domain;

import project.social.domain.Block;
import project.social.domain.enums.RestrictionType;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

public record BlockDto(
        String id,
        String blockerUserId,
        String blockingUserId,
        OffsetDateTime createdA,
        RestrictionType type
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
