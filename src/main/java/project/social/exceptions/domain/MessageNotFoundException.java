package project.social.exceptions.domain;

import project.social.exceptions.base.MessageSendException;

public class MessageNotFoundException extends MessageSendException {
    public MessageNotFoundException(String message) {
        super(message);
    }
}
