package project.social.dto.domain;

import org.springframework.data.domain.Page;

public record FeedDto(
        Page<PostDto> posts
) {

}
