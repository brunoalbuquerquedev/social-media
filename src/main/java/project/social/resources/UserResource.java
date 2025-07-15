package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.domain.Post;
import project.social.dto.domain.UserDto;
import project.social.services.FollowService;
import project.social.services.UserService;
import project.social.util.JwtUtils;
import project.social.util.SecurityUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserResource {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final SecurityUtils securityUtils;
    private final FollowService followService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe() {
        String loggedUserId = securityUtils.getLoggedUserId();
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
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        UserDto dto = userService.findById(id);
        return ResponseEntity.ok(dto.posts());
    }
}
