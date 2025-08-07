package project.social.common.exceptions.domain;

import project.social.common.exceptions.base.ObjectAlreadyExistsException;

public class UserAlreadyExistsException extends ObjectAlreadyExistsException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
