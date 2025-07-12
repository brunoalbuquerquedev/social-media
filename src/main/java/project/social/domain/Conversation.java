package project.social.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "conversations")
public class Conversation {

    private String id;
    private String lastMessageId;
    private OffsetDateTime createdAt;

    @Builder.Default
    private final List<String> participantsIds = new ArrayList<>(2);

    @DBRef(lazy = true)
    private final List<String> messages = new ArrayList<>();
}
