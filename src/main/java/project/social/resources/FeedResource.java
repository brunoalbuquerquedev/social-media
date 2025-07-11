package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.social.domain.User;
import project.social.dto.domain.FeedDto;
import project.social.services.FeedService;
import project.social.services.UserService;
import project.social.util.SecurityUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedResource {

    private FeedService feedService;
    private SecurityUtil securityUtil;
    private UserService userService;

    @GetMapping()
    public ResponseEntity<FeedDto> getFeed() {
        String id = securityUtil.getLoggedUserId();
        FeedDto feedDto = feedService.getFeed(id);
        return ResponseEntity.ok(feedDto);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<FeedDto> getUserFeed(@PathVariable String id) {
        FeedDto feedDto = feedService.getFeed(id);
        return ResponseEntity.ok(feedDto);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<FeedDto> getUserFeedByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        FeedDto feedDto = feedService.getFeed(user.getId());
        return ResponseEntity.ok(feedDto);
    }
}
