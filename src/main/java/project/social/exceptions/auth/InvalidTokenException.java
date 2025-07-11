package project.social.exceptions.auth;

import project.social.exceptions.base.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
