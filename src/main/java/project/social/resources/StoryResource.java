package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.common.annotations.CurrentUser;
import project.social.common.dtos.domain.StoryDto;
import project.social.services.interfaces.IStoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stories")
public class StoryResource {

    private final IStoryService storyService;

    /**
     * Creates a new story.
     *
     * @param content the story content
     * @param mediaUrl the URL to the media (optional)
     * @param currentUserId the ID of the current user
     * @return the created StoryDto
     */
    @PostMapping
    public ResponseEntity<StoryDto> createStory(
            @RequestParam String content,
            @RequestParam(required = false) String mediaUrl,
            @CurrentUser String currentUserId) {
        StoryDto story = storyService.createStory(currentUserId, content, mediaUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(story);
    }

    /**
     * Retrieves active stories for the current user.
     *
     * @param currentUserId the ID of the current user
     * @return a list of active StoryDtos
     */
    @GetMapping("/me")
    public ResponseEntity<List<StoryDto>> getMyStories(@CurrentUser String currentUserId) {
        List<StoryDto> stories = storyService.getActiveStoriesByAuthorId(currentUserId);
        return ResponseEntity.ok(stories);
    }

    /**
     * Retrieves active stories for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of active StoryDtos
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<StoryDto>> getUserStories(@PathVariable String userId) {
        List<StoryDto> stories = storyService.getActiveStoriesByAuthorId(userId);
        return ResponseEntity.ok(stories);
    }

    /**
     * Retrieves all active stories (feed).
     *
     * @return a list of all active StoryDtos
     */
    @GetMapping("/feed")
    public ResponseEntity<List<StoryDto>> getStoriesFeed() {
        List<StoryDto> stories = storyService.getAllActiveStories();
        return ResponseEntity.ok(stories);
    }

    /**
     * Retrieves a story by its ID.
     *
     * @param id the ID of the story
     * @return the StoryDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<StoryDto> getStory(@PathVariable String id) {
        StoryDto story = storyService.getStoryById(id);
        return ResponseEntity.ok(story);
    }

    /**
     * Deletes a story.
     *
     * @param id the ID of the story
     * @param currentUserId the ID of the current user
     * @return no content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(
            @PathVariable String id,
            @CurrentUser String currentUserId) {
        storyService.deleteStory(id);
        return ResponseEntity.noContent().build();
    }
}
