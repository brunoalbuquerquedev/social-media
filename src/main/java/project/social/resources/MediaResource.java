package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.social.common.annotations.CurrentUser;
import project.social.common.dtos.domain.MediaDto;
import project.social.services.interfaces.IMediaService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/media")
public class MediaResource {

    private final IMediaService mediaService;

    /**
     * Uploads a media file.
     *
     * @param file the file to upload
     * @param currentUserId the ID of the current user
     * @return the created MediaDto
     */
    @PostMapping("/upload")
    public ResponseEntity<MediaDto> uploadMedia(
            @RequestParam("file") MultipartFile file,
            @CurrentUser String currentUserId) {
        MediaDto media = mediaService.uploadMedia(file, currentUserId);
        return ResponseEntity.status(HttpStatus.CREATED).body(media);
    }

    /**
     * Retrieves a media file by its ID.
     *
     * @param id the ID of the media
     * @return the MediaDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<MediaDto> getMedia(@PathVariable String id) {
        MediaDto media = mediaService.getMediaById(id);
        return ResponseEntity.ok(media);
    }

    /**
     * Retrieves all media files uploaded by the current user.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param currentUserId the ID of the current user
     * @return a paginated list of MediaDtos
     */
    @GetMapping("/me")
    public ResponseEntity<Page<MediaDto>> getMyMedia(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @CurrentUser String currentUserId) {
        Page<MediaDto> media = mediaService.getMediaByUploadedBy(currentUserId, pageNumber, pageSize);
        return ResponseEntity.ok(media);
    }

    /**
     * Deletes a media file.
     *
     * @param id the ID of the media
     * @param currentUserId the ID of the current user
     * @return no content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(
            @PathVariable String id,
            @CurrentUser String currentUserId) {
        mediaService.deleteMedia(id);
        return ResponseEntity.noContent().build();
    }
}
