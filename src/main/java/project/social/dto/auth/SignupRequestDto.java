package project.social.dto.auth;

public record SignupRequestDto(
        String username,
        String email,
        String role,
        String password
) {

}

