package project.social.exceptions.user;

import project.social.exceptions.base.ObjectAlreadyExistsException;

public class UserAlreadyExistsException extends ObjectAlreadyExistsException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
