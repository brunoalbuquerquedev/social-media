package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import project.social.common.dtos.domain.MediaDto;

public interface IMediaService {
    /**
     * Uploads a media file.
     *
     * @param file the file to upload
     * @param uploadedBy the ID of the user uploading the file
     * @return the created MediaDto
     */
    MediaDto uploadMedia(MultipartFile file, String uploadedBy);

    /**
     * Retrieves a media file by its ID.
     *
     * @param id the ID of the media
     * @return the MediaDto if found
     */
    MediaDto getMediaById(String id);

    /**
     * Retrieves all media files uploaded by a user.
     *
     * @param uploadedBy the ID of the user
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of MediaDtos
     */
    Page<MediaDto> getMediaByUploadedBy(String uploadedBy, int pageNumber, int pageSize);

    /**
     * Deletes a media file.
     *
     * @param id the ID of the media
     */
    void deleteMedia(String id);

    /**
     * Validates a file before upload.
     *
     * @param file the file to validate
     */
    void validateFile(MultipartFile file);
}
