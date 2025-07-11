package project.social.exceptions.block;

import project.social.exceptions.base.ObjectAlreadyExistsException;

public class BlockAlreadyExistsException extends ObjectAlreadyExistsException {
    public BlockAlreadyExistsException(String message) {
        super(message);
    }
}
