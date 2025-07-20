package project.social.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Permission {

    // Permissions for user actions
    READ_PROFILE("READ_PROFILE"),
    UPDATE_PROFILE("UPDATE_PROFILE"),
    SEND_MESSAGE("SEND_MESSAGE"),
    POST_CONTENT("POST_CONTENT"),

    // Permissions for content management
    MUTE_USER("MUTE_USER"),
    BLOCK_USER("BLOCK_USER"),

    // Permissions for post and comment management
    LIKE_POST("LIKE_POST"),
    EDIT_COMMENT("EDIT_COMMENT"),
    CREATE_POST("CREATE_POST"),
    CREATE_COMMENT("CREATE_COMMENT"),
    DELETE_POST("DELETE_POST"),
    DELETE_COMMENT("DELETE_COMMENT"),
    REPORT_POST("REPORT_POST"),
    REPORT_COMMENT("REPORT_COMMENT"),

    // Permissions for viewing content
    VIEW_POST("VIEW_POST"),
    VIEW_LIKES("VIEW_LIKES"),
    VIEW_COMMENTS("VIEW_COMMENTS"),
    VIEW_FOLLOWERS("VIEW_FOLLOWERS"),
    VIEW_FOLLOWING("VIEW_FOLLOWING"),
    VIEW_PROFILE("VIEW_PROFILE"),
    VIEW_MESSAGES("VIEW_MESSAGES"),

    // Permissions for follow management
    FOLLOW_USER("FOLLOW_USER"),
    UNFOLLOW_USER("UNFOLLOW_USER"),


    // Permissions for administrative actions
    BAN_USER("BAN_USER"),
    MANAGE_ROLES("MANAGE_ROLES");

    /**
     * The value of the permission.
     */
    private final String value;

    /**
     * Returns a list of all permission values.
     *
     * @return a list of permission values
     */
    public static List<String> allPermissions() {
        return Arrays.stream(Permission.values())
                .map(Permission::getValue)
                .toList();
    }
}
