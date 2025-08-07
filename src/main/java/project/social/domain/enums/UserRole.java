package project.social.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Getter
@RequiredArgsConstructor()
public enum UserRole {

    // Role definitions for the social media application
    USER("User", 1, true, List.of(
            Permission.READ_PROFILE.getValue(),
            Permission.SEND_MESSAGE.getValue(),
            Permission.POST_CONTENT.getValue(),
            Permission.MUTE_USER.getValue(),
            Permission.BLOCK_USER.getValue(),
            Permission.LIKE_POST.getValue(),
            Permission.EDIT_COMMENT.getValue(),
            Permission.CREATE_POST.getValue(),
            Permission.CREATE_COMMENT.getValue(),
            Permission.DELETE_POST.getValue(),
            Permission.DELETE_COMMENT.getValue(),
            Permission.REPORT_POST.getValue(),
            Permission.REPORT_COMMENT.getValue()
    )),

    // Role for users with elevated permissions
    ADMIN("Administrator", 3, false, Permission.allPermissions());

    private final String label;
    private final int priority;
    private final boolean defaultRole;
    private final List<String> permissions;

    /**
     * Returns the value of the role.
     *
     * @return the value of the role
     */
    public List<SimpleGrantedAuthority> grantedAuthorities() {
        return permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
