package project.social.common.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record JwtTokenDto(
        @NotBlank(message = "Access token is required.") String accessToken,
        @NotBlank(message = "Refresh token is required.") String refreshToken
) {

}
