package project.social.dto.auth;

import java.io.Serial;
import java.io.Serializable;

public class RefreshRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String refreshToken;

    public RefreshRequestDto() {
    }

    public RefreshRequestDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
