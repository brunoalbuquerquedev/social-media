package project.social.common.dtos.domain;

import project.social.domain.Report;
import project.social.domain.enums.ReportContentType;
import project.social.domain.enums.ReportStatus;

import java.time.OffsetDateTime;

public record ReportDto(
        String id,
        String reportedBy,
        ReportContentType contentType,
        String contentId,
        String reason,
        String description,
        ReportStatus status,
        OffsetDateTime createdAt,
        OffsetDateTime reviewedAt,
        String reviewedBy,
        String resolution
) {
    public ReportDto(Report report) {
        this(
                report.getId(),
                report.getReportedBy(),
                report.getContentType(),
                report.getContentId(),
                report.getReason(),
                report.getDescription(),
                report.getStatus(),
                report.getCreatedAt(),
                report.getReviewedAt(),
                report.getReviewedBy(),
                report.getResolution()
        );
    }
}
