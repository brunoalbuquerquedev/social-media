package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.common.annotations.CurrentUser;
import project.social.common.dtos.domain.RetweetDto;
import project.social.services.interfaces.IRetweetService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/retweets")
public class RetweetResource {

    private final IRetweetService retweetService;

    /**
     * Creates a new retweet.
     *
     * @param postId the ID of the post
     * @param currentUserId the ID of the current user
     * @return the created RetweetDto
     */
    @PostMapping("/{postId}")
    public ResponseEntity<RetweetDto> createRetweet(
            @PathVariable String postId,
            @CurrentUser String currentUserId) {
        RetweetDto retweet = retweetService.createRetweet(postId, currentUserId);
        return ResponseEntity.status(HttpStatus.CREATED).body(retweet);
    }

    /**
     * Deletes a retweet.
     *
     * @param postId the ID of the post
     * @param currentUserId the ID of the current user
     * @return no content response
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteRetweet(
            @PathVariable String postId,
            @CurrentUser String currentUserId) {
        retweetService.deleteRetweet(postId, currentUserId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all retweets for a specific post.
     *
     * @param postId the ID of the post
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of RetweetDtos
     */
    @GetMapping("/post/{postId}")
    public ResponseEntity<Page<RetweetDto>> getRetweetsByPost(
            @PathVariable String postId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<RetweetDto> retweets = retweetService.getRetweetsByPostId(postId, pageNumber, pageSize);
        return ResponseEntity.ok(retweets);
    }

    /**
     * Gets the count of retweets for a post.
     *
     * @param postId the ID of the post
     * @return the count of retweets
     */
    @GetMapping("/post/{postId}/count")
    public ResponseEntity<Long> getRetweetCount(@PathVariable String postId) {
        long count = retweetService.getRetweetCount(postId);
        return ResponseEntity.ok(count);
    }
}
