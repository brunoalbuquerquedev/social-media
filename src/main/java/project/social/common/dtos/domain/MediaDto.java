package project.social.common.dtos.domain;

import project.social.domain.Media;

import java.time.OffsetDateTime;

public record MediaDto(
        String id,
        String fileName,
        String fileType,
        Long fileSize,
        String url,
        String uploadedBy,
        OffsetDateTime createdAt
) {
    public MediaDto(Media media) {
        this(
                media.getId(),
                media.getFileName(),
                media.getFileType(),
                media.getFileSize(),
                media.getUrl(),
                media.getUploadedBy(),
                media.getCreatedAt()
        );
    }
}
