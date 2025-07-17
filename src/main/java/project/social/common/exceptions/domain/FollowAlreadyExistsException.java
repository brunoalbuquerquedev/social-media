package project.social.common.exceptions.domain;

import project.social.common.exceptions.base.ObjectAlreadyExistsException;

public class FollowAlreadyExistsException extends ObjectAlreadyExistsException {
    public FollowAlreadyExistsException(String message) {
        super(message);
    }
}
