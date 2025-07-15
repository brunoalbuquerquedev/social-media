package project.social.dto.auth;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;

@Nonnull
public record SignupRequestDto(
        @NotNull String username,
        @NotNull String email,
        @NotNull String role,
        @NotNull String password
) {

}

