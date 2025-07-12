package project.social.exceptions.domain;

import project.social.exceptions.auth.InvalidRequestDataException;

public class InvalidBlockRequestException extends InvalidRequestDataException {
    public InvalidBlockRequestException(String message) {
        super(message);
    }
}
