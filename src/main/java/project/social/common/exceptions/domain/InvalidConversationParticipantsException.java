package project.social.common.exceptions.domain;

import project.social.common.exceptions.base.InvalidRequestException;

public class InvalidConversationParticipantsException extends InvalidRequestException {
    public InvalidConversationParticipantsException(String message) {
        super(message);
    }
}
