package project.social.dto.auth;

 import jakarta.validation.constraints.NotNull;

public record AuthResponseDto(
        @NotNull(message = "Token object is required") JwtTokenDto token
) {

}
