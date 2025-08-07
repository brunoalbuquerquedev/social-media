package project.social.common.exceptions.domain;

import project.social.common.exceptions.base.MessageSendException;

public class MessageNotFoundException extends MessageSendException {
    public MessageNotFoundException(String message) {
        super(message);
    }
}
