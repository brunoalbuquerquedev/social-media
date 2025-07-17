package project.social.common.exceptions.auth;

import project.social.common.exceptions.base.AuthenticationException;

public class IncorrectPasswordException extends AuthenticationException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
