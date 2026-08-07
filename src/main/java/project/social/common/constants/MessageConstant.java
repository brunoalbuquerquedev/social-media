package project.social.common.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageConstant {
    USER_ALREADY_EXISTS("This email already has an account."),
    USERNAME_UNAVAILABLE("This username is unavailable."),
    USER_NOT_FOUND("User not found."),
    INCORRECT_PASSWORD("The password is incorrect."),
    INVALID_REQUEST_DATA("The request data is invalid."),
    INVALID_REFRESH_TOKEN_DATA("Refresh token not found or already revoked."),
    INVALID_TOKEN("Token is invalid or expired."),
    UNSUPPORTED_OPERATION("This operation is not supported.");

    private final String message;
}
