package project.social.common.exceptions.domain;

import project.social.common.exceptions.base.ObjectAlreadyExistsException;

public class BlockAlreadyExistsException extends ObjectAlreadyExistsException {
    public BlockAlreadyExistsException(String message) {
        super(message);
    }
}
