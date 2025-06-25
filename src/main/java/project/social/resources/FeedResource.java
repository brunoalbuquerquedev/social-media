package project.social.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.social.domain.User;
import project.social.dto.FeedDto;
import project.social.dto.FeedResponseDto;
import project.social.services.FeedService;
import project.social.services.UserService;
import project.social.util.SecurityUtil;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedResource {

    @Autowired
    private FeedService feedService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<FeedResponseDto> getTimeline() {
        String id = securityUtil.getLoggedUserId();
        List<FeedDto> feed = feedService.getTimelineForUser(id);
        return ResponseEntity.ok(new FeedResponseDto(feed));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<FeedResponseDto> getUserFeedByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        List<FeedDto> feed = feedService.getTimelineForUser(user.getId());
        return ResponseEntity.ok(new FeedResponseDto(feed));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedResponseDto> timeline(@PathVariable String id) {
        List<FeedDto> feed = feedService.getTimelineForUser(id);
        return ResponseEntity.ok(new FeedResponseDto(feed));
    }
}
