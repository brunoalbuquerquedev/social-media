package project.social.dto.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record FeedDto(
        List<PostDto> posts
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
