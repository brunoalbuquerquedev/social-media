package project.social.dto.auth;

public record LoginRequestDto(
        String email,
        String password
) {

}
