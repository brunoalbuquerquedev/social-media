package project.social.common.exceptions.auth;

import project.social.common.exceptions.base.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
