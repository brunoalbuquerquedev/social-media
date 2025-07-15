package project.social.dto.domain;

import jakarta.validation.constraints.NotNull;
import project.social.domain.Post;
import project.social.domain.User;

import java.time.OffsetDateTime;
import java.util.List;

public record UserDto(
        @NotNull String id,
        @NotNull String username,
        @NotNull String email,
        @NotNull String role,
        @NotNull String password,
        String fullName,
        String bio,
        String profilePictureUrl,
        String location,
        OffsetDateTime birthDate,
        @NotNull OffsetDateTime createdAt,
        @NotNull Boolean isActive,
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
