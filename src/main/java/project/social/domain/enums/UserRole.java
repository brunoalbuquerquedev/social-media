package project.social.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Getter
@RequiredArgsConstructor()
public enum UserRole {
    USER("User", 1, true, List.of(
            Permission.READ_PROFILE.getValue(),
            Permission.SEND_MESSAGE.getValue(),
            Permission.POST_CONTENT.getValue(),
            Permission.MUTE_USER.getValue(),
            Permission.BLOCK_USER.getValue()
    )),
    ADMIN("Administrator", 3, false, Permission.allPermissions());

    private final String label;
    private final int priority;
    private final boolean defaultRole;
    private final List<String> permissions;

    public List<SimpleGrantedAuthority> grantedAuthorities() {
        return permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
