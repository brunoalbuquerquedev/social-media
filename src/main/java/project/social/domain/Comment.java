package project.social.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "comments")
public class Comment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String postId;
    private String authorId;
    private String content;
    private OffsetDateTime createdAt;
}
