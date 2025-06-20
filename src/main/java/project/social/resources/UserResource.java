package project.social.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import project.social.domain.Post;
import project.social.domain.User;
import project.social.dto.TimelinePostDto;
import project.social.dto.TimelineResponseDto;
import project.social.dto.UserDto;
import project.social.services.TimelineService;
import project.social.services.UserService;
import project.social.util.JwtUtil;
import project.social.util.SecurityUtil;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private TimelineService timelineService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SecurityUtil securityUtil;

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> findAll() {
        List<User> list = userService.findAll();
        List<UserDto> dtoList = list.stream().map(UserDto::new).toList();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(new UserDto(user));
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user.getPosts());
    }

    @PostMapping("/{id}/follow")
    public ResponseEntity<Void> follow(@PathVariable("id") String followingId) {
        String followerId = securityUtil.getLoggedUserId();
        User user = userService.followUser(followerId, followingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/timeline")
    public ResponseEntity<TimelineResponseDto> timeline(@PathVariable String id) {
        List<TimelinePostDto> posts = timelineService.getTimelineForUser(id);
        return ResponseEntity.ok(new TimelineResponseDto(posts));
    }

}
