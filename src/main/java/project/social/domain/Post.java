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
    private String authorId;
    private String authorUsername;
    private String content;
    private String authorProfilePictureUrl;
    private OffsetDateTime createdAt;
    private boolean hasUserLiked;

    @DBRef(lazy = true)
    private List<Like> likes;

    @DBRef(lazy = true)
    private List<CommentDto> comments;

    @DBRef(lazy = true)
    private List<String> mediaUrl;

}
