package project.social.common.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppConstants {
    ACCESS_TOKEN_EXPIRATION(10 * 60 * 1000),
    REFRESH_TOKEN_EXPIRATION(24 * 60 * 60 * 1000);

    private final long value;
}
