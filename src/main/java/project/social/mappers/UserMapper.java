package project.social.mappers;

import project.social.domain.User;
import project.social.dto.domain.UserDto;

public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) return null;

        return new UserDto(
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
                user.getUsersFollowedByMe(),
                user.getUsersWhoFollowMe(),
                user.getUsersBlockedByMe(),
                user.getUsersWhoBlockMe()
        );
    }

    public static User fromDto(UserDto dto) {
        if (dto == null) return null;

        return User.builder()
                .id(dto.id())
                .username(dto.username())
                .email(dto.email())
                .password(dto.password())
                .fullName(dto.fullName())
                .bio(dto.bio())
                .profilePictureUrl(dto.profilePictureUrl())
                .location(dto.location())
                .birthDate(dto.birthDate())
                .createdAt(dto.createdAt())
                .isActive(dto.isActive())
                .lastLogin(dto.lastLogin())
                .posts(dto.posts())
                .usersFollowedByMe(dto.followersIds())
                .usersWhoFollowMe(dto.followingIds())
                .usersBlockedByMe(dto.usersBlockedByMe())
                .usersWhoBlockMe(dto.usersWhoBlockedMe())
                .build();
    }
}
