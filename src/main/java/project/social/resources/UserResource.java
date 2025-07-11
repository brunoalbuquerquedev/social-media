package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.domain.Post;
import project.social.domain.User;
import project.social.dto.domain.UserDto;
import project.social.services.FollowService;
import project.social.services.UserService;
import project.social.util.JwtUtil;
import project.social.util.SecurityUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserResource {

    private UserService userService;
    private JwtUtil jwtUtil;
    private SecurityUtil securityUtil;
    private FollowService followService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe() {
        String id = securityUtil.getLoggedUserId();
        UserDto dto = userService.findById(id);
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
