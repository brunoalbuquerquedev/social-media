package project.social.dto.auth;

import java.io.Serial;
import java.io.Serializable;

public record JwtTokenResponse(
        String accessToken,
        String refreshToken
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
