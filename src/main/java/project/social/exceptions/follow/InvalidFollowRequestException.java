package project.social.exceptions.follow;

import project.social.exceptions.auth.InvalidRequestDataException;

public class InvalidFollowRequestException extends InvalidRequestDataException {
    public InvalidFollowRequestException(String message) {
        super(message);
    }
}
