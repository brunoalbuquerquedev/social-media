package project.social.exceptions.auth;

import project.social.exceptions.base.AuthenticationException;

public class IncorrectPasswordException extends AuthenticationException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
