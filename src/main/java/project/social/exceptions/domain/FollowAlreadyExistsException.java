package project.social.exceptions.domain;

import project.social.exceptions.base.ObjectAlreadyExistsException;

public class FollowAlreadyExistsException extends ObjectAlreadyExistsException {
    public FollowAlreadyExistsException(String message) {
        super(message);
    }
}
