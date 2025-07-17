package project.social.common.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record RefreshRequestDto(
        @NotBlank(message = "Refresh token is required.") String refreshToken
) {

}
