package project.social.domain.enums;

public enum SeenStatus {

    /**
     * The message is pending and has not yet been sent.
     */
    PENDING,

    /**
     * The message has been sent but not yet delivered.
     */
    SENT,

    /**
     * The message has been delivered to the recipient.
     */
    DELIVERED,

    /**
     * The message has been seen by the recipient.
     */
    SEEN
}
