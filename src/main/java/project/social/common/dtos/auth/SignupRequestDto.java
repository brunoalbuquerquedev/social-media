package project.social.common.dtos.auth;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Nonnull
public record SignupRequestDto(
        @NotBlank(message = "Username is required.") String username,
        @Email(message = "Email is invalid.") String email,
        @NotBlank(message = "Password is required.") String password,
        @NotBlank(message = "Role is required.") String role
) {

}
