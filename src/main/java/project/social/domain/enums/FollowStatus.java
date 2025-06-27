package project.social.domain.enums;

/**
 * Represents the status of a follow relationship or request between two users.
 */
public enum FollowStatus {

    /**
     * The user is following the other user.
     */
    FOLLOWING,

    /**
     * One of the users has blocked the other. Prevents following, commenting, liking, etc.
     */
    BLOCKED,

    /**
     * The user has manually unfollowed the other user.
     */
    REMOVED,

    /**
     * The user is following but has muted the other user (e.g., to hide content in their feed).
     */
    MUTED
}
