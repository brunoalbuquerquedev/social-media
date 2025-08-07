package project.social.common.exceptions.domain;

import project.social.common.exceptions.auth.InvalidRequestDataException;

public class InvalidFollowRequestException extends InvalidRequestDataException {
    public InvalidFollowRequestException(String message) {
        super(message);
    }
}
