package project.social.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import project.social.dto.domain.CommentDto;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "posts")
public class Post implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String authorUsername;
    private String authorId;
    private String content;
    private String title;
    private OffsetDateTime createdAt;
    private String authorProfilePictureUrl;
    private boolean hasUserLiked;

    @DBRef(lazy = true)
    private final List<Like> likes = new ArrayList<>();

    @DBRef(lazy = true)
    private final List<CommentDto> comments = new ArrayList<>();

    @DBRef(lazy = true)
    private final List<String> mediaUrl = new ArrayList<>();

}
