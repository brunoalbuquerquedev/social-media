package project.social.common.exceptions.domain;

import project.social.common.exceptions.auth.InvalidRequestDataException;

public class InvalidBlockRequestException extends InvalidRequestDataException {
    public InvalidBlockRequestException(String message) {
        super(message);
    }
}
