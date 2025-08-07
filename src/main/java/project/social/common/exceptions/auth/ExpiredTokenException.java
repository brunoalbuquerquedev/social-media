package project.social.common.exceptions.auth;

import project.social.common.exceptions.base.AuthenticationException;

public class ExpiredTokenException extends AuthenticationException {
    public ExpiredTokenException(String message) {
        super(message);
    }
}
