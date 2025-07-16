package project.social.dto.auth;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
        @NotNull String email,
        @NotNull String password
) {

}
