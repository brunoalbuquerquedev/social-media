package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.common.dtos.domain.StoryDto;

import java.util.List;

public interface IStoryService {
    /**
     * Creates a new story.
     *
     * @param authorId the ID of the author
     * @param content the story content
     * @param mediaUrl the URL to the media (optional)
     * @return the created StoryDto
     */
    StoryDto createStory(String authorId, String content, String mediaUrl);

    /**
     * Retrieves stories by author ID.
     *
     * @param authorId the ID of the author
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of StoryDtos
     */
    Page<StoryDto> getStoriesByAuthorId(String authorId, int pageNumber, int pageSize);

    /**
     * Retrieves active stories (non-expired) for a user.
     *
     * @param authorId the ID of the author
     * @return a list of active StoryDtos
     */
    List<StoryDto> getActiveStoriesByAuthorId(String authorId);

    /**
     * Retrieves all active stories.
     *
     * @return a list of all active StoryDtos
     */
    List<StoryDto> getAllActiveStories();

    /**
     * Retrieves a story by its ID.
     *
     * @param id the ID of the story
     * @return the StoryDto if found
     */
    StoryDto getStoryById(String id);

    /**
     * Deletes a story.
     *
     * @param id the ID of the story
     */
    void deleteStory(String id);

    /**
     * Deletes expired stories.
     */
    void deleteExpiredStories();
}
