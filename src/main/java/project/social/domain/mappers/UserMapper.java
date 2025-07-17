package project.social.domain.mappers;

import project.social.common.dtos.domain.user.UserResponseDto;
import project.social.common.dtos.domain.user.UserUpdateDto;
import project.social.domain.User;
import project.social.common.dtos.domain.user.UserDto;

public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) return null;

        return new UserDto(
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
    
    public static User fromUpdateDto(User user, UserUpdateDto dto) {
        if (dto == null) return null;

        return User.builder()
                .id(dto.id())
                .username(dto.username())
                .email(user.getEmail())
                .password(user.getPassword())
                .fullName(dto.fullName())
                .bio(dto.bio())
                .profilePictureUrl(dto.profilePictureUrl())
                .location(dto.location())
                .birthDate(dto.birthDate())
                .createdAt(user.getCreatedAt())
                .isActive(user.getIsActive())
                .lastLogin(user.getLastLogin())
                .posts(user.getPosts())
                .usersFollowedByMe(user.getUsersFollowedByMe())
                .usersWhoFollowMe(user.getUsersWhoFollowMe())
                .usersBlockedByMe(user.getUsersBlockedByMe())
                .usersWhoBlockMe(user.getUsersWhoBlockMe())
                .build();
    }

    public static User fromResponseDto(User user, UserResponseDto dto) {
        if (dto == null) return null;

        return User.builder()
                .id(dto.id())
                .username(dto.username())
                .email(dto.email())
                .role(user.getRole())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .bio(user.getBio())
                .profilePictureUrl(dto.profilePictureUrl())
                .location(user.getLocation())
                .birthDate(user.getBirthDate())
                .createdAt(user.getCreatedAt())
                .isActive(user.getIsActive())
                .lastLogin(user.getLastLogin())
                .posts(user.getPosts())
                .usersFollowedByMe(user.getUsersFollowedByMe())
                .usersWhoFollowMe(user.getUsersWhoFollowMe())
                .usersBlockedByMe(user.getUsersBlockedByMe())
                .usersWhoBlockMe(user.getUsersWhoBlockMe())
                .build();
    }
}
