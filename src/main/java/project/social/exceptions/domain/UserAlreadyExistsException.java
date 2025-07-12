package project.social.exceptions.domain;

import project.social.exceptions.base.ObjectAlreadyExistsException;

public class UserAlreadyExistsException extends ObjectAlreadyExistsException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
