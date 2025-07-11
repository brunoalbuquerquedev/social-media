package project.social.dto.domain;

import project.social.domain.Post;
import project.social.domain.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public record UserDto(
        String id,
        String username,
        String email,
        String password,
        String fullName,
        String bio,
        String profilePictureUrl,
        String location,
        Date birthDate,
        Date createdAt,
        Boolean isActive,
        Date lastLogin,
        List<Post> posts,
        List<String> followersIds,
        List<String> followingIds,
        List<String> usersBlockedByMe,
        List<String> usersWhoBlockedMe
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserDto(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
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
                user.getFollowersIds(),
                user.getFollowingIds(),
                user.getUsersBlockedByMe(),
                user.getUsersWhoBlockedMe()
        );
    }
}
