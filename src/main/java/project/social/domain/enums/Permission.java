package project.social.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Permission {
    READ_PROFILE("READ_PROFILE"),
    SEND_MESSAGE("SEND_MESSAGE"),
    POST_CONTENT("POST_CONTENT"),

    MUTE_USER("MUTE_USER"),
    BLOCK_USER("BLOCK_USER"),

    EDIT_COMMENT("EDIT_COMMENT"),
    DELETE_COMMENT("DELETE_COMMENT"),

    BAN_USER("BAN_USER"),
    MANAGE_ROLES("MANAGE_ROLES");

    private final String value;

    public static List<String> allPermissions() {
        return Arrays.stream(Permission.values())
                .map(Permission::getValue)
                .toList();
    }
}
