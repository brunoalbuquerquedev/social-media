package project.social.exceptions.domain;

import project.social.exceptions.base.InvalidRequestDataException;

public class InvalidConversationParticipantsException extends InvalidRequestDataException {
    public InvalidConversationParticipantsException(String message) {
        super(message);
    }
}
