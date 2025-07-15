package project.social.dto.auth;

import jakarta.validation.constraints.NotNull;

public record RefreshRequestDto(
        @NotNull String refreshToken
) {

}
