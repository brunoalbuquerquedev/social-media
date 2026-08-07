package project.social.common.dtos.domain;

import project.social.common.dtos.domain.user.UserResponseDto;
import project.social.domain.Post;

import java.util.List;

public record SearchResultDto(
        List<UserResponseDto> users,
        List<PostDto> posts,
        List<HashtagDto> hashtags
) {
}
