package project.social.dto.auth;

 import jakarta.validation.constraints.NotNull;

public record AuthResponseDto(
        @NotNull JwtTokenResponse token
) {

}
