package project.social.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import project.social.domain.enums.ReportContentType;
import project.social.domain.enums.ReportStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "reports")
public class Report implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String reportedBy;
    private ReportContentType contentType;
    private String contentId;
    private String reason;
    private String description;
    private ReportStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime reviewedAt;
    private String reviewedBy;
    private String resolution;
}
