package project.social.dto.domain;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

public record FeedDto(
        @NotNull Page<PostDto> posts
) {

}
