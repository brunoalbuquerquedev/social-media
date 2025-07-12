package project.social.dto.domain;

import java.util.List;

public record FeedDto(
        List<PostDto> posts
) {

}
