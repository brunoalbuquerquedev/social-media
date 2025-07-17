package project.social.common.dtos.domain.user;

import jakarta.validation.constraints.NotBlank;
import project.social.domain.User;

import java.time.OffsetDateTime;

public record UserUpdateDto(
        @NotBlank String id,
        @NotBlank String username,
        @NotBlank String fullName,
        @NotBlank String bio,
        @NotBlank String profilePictureUrl,
        @NotBlank String location,
        @NotBlank OffsetDateTime birthDate
) {
    public UserUpdateDto(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getBio(),
                user.getProfilePictureUrl(),
                user.getLocation(),
                user.getBirthDate()
        );
    }
}
