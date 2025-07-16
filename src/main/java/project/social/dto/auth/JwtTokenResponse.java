package project.social.dto.auth;

import jakarta.validation.constraints.NotNull;

public record JwtTokenResponse(
        @NotNull String accessToken,
        @NotNull String refreshToken
) {

}
