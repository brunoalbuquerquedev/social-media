package project.social.dto.auth;

public record JwtTokenResponse(
        String accessToken,
        String refreshToken
) {

}
