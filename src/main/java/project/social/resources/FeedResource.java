package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.dto.domain.FeedDto;
import project.social.dto.domain.UserDto;
import project.social.services.FeedService;
import project.social.services.UserService;
import project.social.util.SecurityUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feed")
public class FeedResource {

    private final FeedService feedService;
    private final SecurityUtils securityUtils;
    private final UserService userService;

    /**
     * Retrieves the feed for the logged-in user.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize   the number of items per page
     * @return a FeedDto containing the posts for the user's feed
     */
    @GetMapping()
    public ResponseEntity<FeedDto> getFeed(@RequestParam(defaultValue = "0") int pageNumber,
                                           @RequestParam(defaultValue = "10") int pageSize) {
        String loggedUserId = securityUtils.getLoggedUserId();
        FeedDto feedDto = feedService.getFeed(loggedUserId, pageNumber, pageSize);
        return ResponseEntity.ok(feedDto);
    }

    /**
     * Retrieves the feed for a user by their ID.
     *
     * @param id         the ID of the user
     * @param pageNumber the page number to retrieve
     * @param pageSize   the number of items per page
     * @return a FeedDto containing the posts for the user's feed
     */
    @GetMapping("/user-id/{id}")
    public ResponseEntity<FeedDto> getUserFeed(@PathVariable String id,
                                               @RequestParam(defaultValue = "0") int pageNumber,
                                               @RequestParam(defaultValue = "10") int pageSize) {
        FeedDto feedDto = feedService.getFeed(id, pageNumber, pageSize);
        return ResponseEntity.ok(feedDto);
    }

    /**
     * Retrieves the feed for a user by their username.
     *
     * @param username   the username of the user
     * @param pageNumber the page number to retrieve
     * @param pageSize   the number of items per page
     * @return a FeedDto containing the posts for the user's feed
     */
    @GetMapping("/user-username/{username}")
    public ResponseEntity<FeedDto> getUserFeedByUsername(@PathVariable String username,
                                                         @RequestParam(defaultValue = "0") int pageNumber,
                                                         @RequestParam(defaultValue = "10") int pageSize) {
        UserDto dto = userService.findByUsername(username);
        FeedDto feedDto = feedService.getFeed(dto.id(), pageNumber, pageSize);
        return ResponseEntity.ok(feedDto);
    }
}
