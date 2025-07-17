package project.social.common.exceptions.auth;

import project.social.common.exceptions.base.AuthenticationException;

public class InvalidRequestDataException extends AuthenticationException {
    public InvalidRequestDataException(String message) {
        super(message);
    }
}
