package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.social.dto.domain.FeedDto;
import project.social.dto.domain.UserDto;
import project.social.services.FeedService;
import project.social.services.UserService;
import project.social.util.SecurityUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedResource {

    private final FeedService feedService;
    private final SecurityUtils securityUtils;
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<FeedDto> getFeed() {
        String loggedUserId = securityUtils.getLoggedUserId();
        FeedDto feedDto = feedService.getFeed(loggedUserId);
        return ResponseEntity.ok(feedDto);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<FeedDto> getUserFeed(@PathVariable String id) {
        FeedDto feedDto = feedService.getFeed(id);
        return ResponseEntity.ok(feedDto);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<FeedDto> getUserFeedByUsername(@PathVariable String username) {
        UserDto dto = userService.findByUsername(username);
        FeedDto feedDto = feedService.getFeed(dto.id());
        return ResponseEntity.ok(feedDto);
    }
}
