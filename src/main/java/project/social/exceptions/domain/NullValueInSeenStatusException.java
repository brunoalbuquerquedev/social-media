package project.social.exceptions.domain;

public class NullValueInSeenStatusException extends RuntimeException {
    public NullValueInSeenStatusException(String message) {
        super(message);
    }
}
