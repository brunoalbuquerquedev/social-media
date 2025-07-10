package project.social.exceptions.auth;

import project.social.exceptions.base.AuthenticationException;

public class InvalidRequestDataException extends AuthenticationException {
    public InvalidRequestDataException(String message) {
        super(message);
    }
}
