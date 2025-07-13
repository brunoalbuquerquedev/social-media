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
     * The user is following and followed by the other.
     */
    MUTUAL_FOLLOWING,

    /**
     * The user has manually unfollowed the other user.
     */
    REMOVED
}
