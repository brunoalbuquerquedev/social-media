package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.dto.domain.PostDto;
import project.social.dto.domain.UserDto;
import project.social.services.FollowService;
import project.social.services.PostService;
import project.social.services.UserService;
import project.social.util.JwtUtil;
import project.social.util.SecurityUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserResource {

    private UserService userService;
    private JwtUtil jwtUtil;
    private SecurityUtil securityUtil;
    private FollowService followService;
    private PostService postService;

    @GetMapping("/all")
    public ResponseEntity<Page<UserDto>> findAll(@RequestParam(defaultValue = "0") int pageNumber,
                                                 @RequestParam(defaultValue = "10") int pageSize) {
        Page<UserDto> page = userService.findAll(pageNumber, pageSize);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe() {
        String loggedUserId = securityUtil.getLoggedUserId();
        UserDto dto = userService.findById(loggedUserId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/me/update")
    public ResponseEntity<Void> updateMe() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        UserDto dto = userService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable String username) {
        UserDto dto = userService.findByUsername(username);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/id/{id}/posts")
    public ResponseEntity<Page<PostDto>> findPosts(@PathVariable String id,
                                                   @RequestParam(defaultValue = "0") int pageNumber,
                                                   @RequestParam(defaultValue = "10") int pageSize) {
        Page<PostDto> page = postService.findAllById(id, pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }
}
