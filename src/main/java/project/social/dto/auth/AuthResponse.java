package project.social.dto.auth;

import project.social.util.JwtTokenResponse;

import java.io.Serial;
import java.io.Serializable;

public class AuthResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private JwtTokenResponse token;

    public AuthResponse() {
    }

    public AuthResponse(JwtTokenResponse token) {
        this.token = token;
    }

    public JwtTokenResponse getToken() {
        return token;
    }

    public void setToken(JwtTokenResponse token) {
        this.token = token;
    }
}

