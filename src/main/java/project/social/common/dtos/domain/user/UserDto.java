package project.social.common.dtos.domain.user;

import project.social.domain.Post;
import project.social.domain.User;

import java.time.OffsetDateTime;
import java.util.List;

public record UserDto(
        String id,
        String username,
        String email,
        String role,
        String password,
        String fullName,
        String bio,
        String profilePictureUrl,
        String location,
        OffsetDateTime birthDate,
        OffsetDateTime createdAt,
        Boolean isActive,
        OffsetDateTime lastLogin,
        List<Post> posts,
        List<String> followersIds,
        List<String> followingIds,
        List<String> usersBlockedByMe,
        List<String> usersWhoBlockedMe
) {
    public UserDto(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getPassword(),
                user.getFullName(),
                user.getBio(),
                user.getProfilePictureUrl(),
                user.getLocation(),
                user.getBirthDate(),
                user.getCreatedAt(),
                user.getIsActive(),
                user.getLastLogin(),
                user.getPosts(),
                user.getUsersFollowedByMe(),
                user.getUsersWhoFollowMe(),
                user.getUsersBlockedByMe(),
                user.getUsersWhoBlockMe()
        );
    }
}
