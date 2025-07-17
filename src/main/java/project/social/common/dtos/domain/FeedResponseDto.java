package project.social.common.dtos.domain;

import org.springframework.data.domain.Page;

public record FeedResponseDto(
        Page<PostDto> posts
) {

}
