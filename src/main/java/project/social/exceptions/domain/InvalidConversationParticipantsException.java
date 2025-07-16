package project.social.exceptions.domain;

import project.social.exceptions.base.InvalidRequestException;

public class InvalidConversationParticipantsException extends InvalidRequestException {
    public InvalidConversationParticipantsException(String message) {
        super(message);
    }
}
