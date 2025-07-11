package project.social.dto.auth;

import java.io.Serial;
import java.io.Serializable;

public record LoginRequestDto(
        String email,
        String password
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
