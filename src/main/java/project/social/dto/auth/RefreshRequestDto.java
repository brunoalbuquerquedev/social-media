package project.social.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record RefreshRequestDto(
        @NotBlank(message = "Refresh token is required") String refreshToken
) {

}
