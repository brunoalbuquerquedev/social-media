package project.social.domain.enums;

public enum RestrictionType {
    /**
     * One of the users has blocked the other. Prevents following, commenting, liking, etc. Also removes the mutual
     * following.
     */
    BLOCK,

    /**
     * The user is following but has muted the other user (e.g., to hide content in their feed).
     */
    MUTE
}
