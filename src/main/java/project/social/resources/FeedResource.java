package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.common.annotations.CurrentUser;
import project.social.common.dtos.domain.FeedResponseDto;
import project.social.common.dtos.domain.user.UserDto;
import project.social.common.dtos.domain.user.UserResponseDto;
import project.social.services.FeedService;
import project.social.services.UserService;
import project.social.common.utils.SecurityUtils;

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
    public ResponseEntity<FeedResponseDto> getFeed(@RequestParam(defaultValue = "0") int pageNumber,
                                                   @RequestParam(defaultValue = "10") int pageSize,
                                                   @CurrentUser String currentUserId) {
        FeedResponseDto feedResponseDto = feedService.getFeed(currentUserId, pageNumber, pageSize);
        return ResponseEntity.ok(feedResponseDto);
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
    public ResponseEntity<FeedResponseDto> getUserFeed(@PathVariable String id,
                                                       @RequestParam(defaultValue = "0") int pageNumber,
                                                       @RequestParam(defaultValue = "10") int pageSize) {
        FeedResponseDto feedResponseDto = feedService.getFeed(id, pageNumber, pageSize);
        return ResponseEntity.ok(feedResponseDto);
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
    public ResponseEntity<FeedResponseDto> getUserFeedByUsername(@PathVariable String username,
                                                                 @RequestParam(defaultValue = "0") int pageNumber,
                                                                 @RequestParam(defaultValue = "10") int pageSize) {
        UserResponseDto dto = userService.findByUsername(username);
        FeedResponseDto feedResponseDto = feedService.getFeed(dto.id(), pageNumber, pageSize);
        return ResponseEntity.ok(feedResponseDto);
    }
}
