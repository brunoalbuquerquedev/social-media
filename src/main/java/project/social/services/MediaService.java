package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.social.common.dtos.domain.MediaDto;
import project.social.common.exceptions.base.ObjectNotFoundException;
import project.social.domain.Media;
import project.social.repositories.MediaRepository;
import project.social.services.interfaces.IMediaService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService implements IMediaService {

    private final MediaRepository mediaRepository;

    @Value("${media.upload.path:uploads}")
    private String uploadPath;

    private static final long MAX_FILE_SIZE = 50 * 1024 * 1024; // 50MB
    private static final String[] ALLOWED_TYPES = {"image/jpeg", "image/png", "image/gif", "video/mp4"};

    @Override
    public MediaDto uploadMedia(MultipartFile file, String uploadedBy) {
        validateFile(file);

        try {
            String fileName = generateFileName(file.getOriginalFilename());
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Path filePath = uploadDir.resolve(fileName);
            Files.write(filePath, file.getBytes());

            Media media = Media.builder()
                    .fileName(fileName)
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .url("/" + uploadPath + "/" + fileName)
                    .uploadedBy(uploadedBy)
                    .createdAt(OffsetDateTime.now())
                    .build();

            Media saved = mediaRepository.save(media);
            return new MediaDto(saved);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload media: " + e.getMessage());
        }
    }

    @Override
    public MediaDto getMediaById(String id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Media not found."));
        return new MediaDto(media);
    }

    @Override
    public Page<MediaDto> getMediaByUploadedBy(String uploadedBy, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return mediaRepository.findByUploadedBy(uploadedBy, pageable).map(MediaDto::new);
    }

    @Override
    public void deleteMedia(String id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Media not found."));

        try {
            Path filePath = Paths.get(uploadPath).resolve(media.getFileName());
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete media file: " + e.getMessage());
        }

        mediaRepository.deleteById(id);
    }

    @Override
    public void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty.");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("File size exceeds 50MB limit.");
        }

        boolean validType = false;
        for (String type : ALLOWED_TYPES) {
            if (type.equals(file.getContentType())) {
                validType = true;
                break;
            }
        }

        if (!validType) {
            throw new IllegalArgumentException("File type not allowed.");
        }
    }

    private String generateFileName(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID() + extension;
    }
}
