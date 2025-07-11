package project.social.exceptions.follow;

import project.social.exceptions.base.ObjectAlreadyExistsException;

public class FollowAlreadyExistsException extends ObjectAlreadyExistsException {
    public FollowAlreadyExistsException(String message) {
        super(message);
    }
}
