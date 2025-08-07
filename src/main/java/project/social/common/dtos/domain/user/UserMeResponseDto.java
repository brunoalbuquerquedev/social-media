package project.social.common.dtos.domain.user;

import project.social.domain.User;

import java.time.OffsetDateTime;

public record UserMeResponseDto(
        String username,
        String email,
        String fullName,
        String bio,
        String profilePictureUrl,
        String location,
        OffsetDateTime birthDate,
        OffsetDateTime createdAt
) {
    public UserMeResponseDto(User user) {
        this(
                user.getUsername(),
                user.getEmail(),
                user.getFullName(),
                user.getBio(),
                user.getProfilePictureUrl(),
                user.getLocation(),
                user.getBirthDate(),
                user.getCreatedAt()
        );
    }
}
