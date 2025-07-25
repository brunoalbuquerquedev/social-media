package project.social.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@Document(collection = "users")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String username;
    private String email;
    private String role;
    private String password;

    private String fullName;
    private String bio;
    private String profilePictureUrl;
    private String location;
    private OffsetDateTime birthDate;

    private OffsetDateTime createdAt;
    private Boolean isActive;
    private OffsetDateTime lastLogin;

    @DBRef(lazy = true)
    private List<Post> posts;

    @DBRef(lazy = true)
    private List<String> usersFollowedByMe;

    @DBRef(lazy = true)
    private List<String> usersWhoFollowMe;

    @DBRef(lazy = true)
    private List<String> usersBlockedByMe;

    @DBRef(lazy = true)
    private List<String> usersWhoBlockMe;
}
