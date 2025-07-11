package project.social.exceptions.auth;

import project.social.exceptions.base.AuthenticationException;

public class ExpiredTokenException extends AuthenticationException {
    public ExpiredTokenException(String message) {
        super(message);
    }
}
