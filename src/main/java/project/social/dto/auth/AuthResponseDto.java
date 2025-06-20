package project.social.dto.auth;

import java.io.Serial;
import java.io.Serializable;

public class AuthResponseDto implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    private JwtTokenResponse token;

    public AuthResponseDto() {
    }

    public AuthResponseDto(JwtTokenResponse token) {
        this.token = token;
    }

    public JwtTokenResponse getToken() {
        return token;
    }

    public void setToken(JwtTokenResponse token) {
        this.token = token;
    }
}

