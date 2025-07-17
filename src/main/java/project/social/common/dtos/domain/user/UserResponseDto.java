package project.social.common.dtos.domain.user;

import project.social.domain.Post;
import project.social.domain.User;

import java.time.OffsetDateTime;
import java.util.List;

public record UserResponseDto(
        String id,
        String username,
        String email,
        String profilePictureUrl,
        OffsetDateTime createdAt,
        OffsetDateTime lastLogin,
        List<Post> posts,
        List<String> followersIds,
        List<String> followingIds,
        List<String> usersBlockedByMe
) {
    public UserResponseDto(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getProfilePictureUrl(),
                user.getCreatedAt(),
                user.getLastLogin(),
                user.getPosts(),
                user.getUsersFollowedByMe(),
                user.getUsersWhoFollowMe(),
                user.getUsersBlockedByMe()
        );
    }
}
