package project.social.common.exceptions.domain;

public class NullValueInSeenStatusException extends RuntimeException {
    public NullValueInSeenStatusException(String message) {
        super(message);
    }
}
