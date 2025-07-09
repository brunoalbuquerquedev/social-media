package project.social.services.exceptions;

public class BlockAlreadyExistsException extends RuntimeException {
    public BlockAlreadyExistsException(String message) {
        super(message);
    }
}
