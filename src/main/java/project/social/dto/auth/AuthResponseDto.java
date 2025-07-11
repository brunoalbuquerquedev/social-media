package project.social.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

public record AuthResponseDto(
        JwtTokenResponse token
) implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;
}

