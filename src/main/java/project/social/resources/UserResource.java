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
        List<User> list = userService.findAll();

        List<UserDto> dtoList = list.stream()
                .map(UserDto::new)
                .toList();

        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe() {
        String id = securityUtil.getLoggedUserId();
        UserDto dto = new UserDto(userService.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/me/update")
    public ResponseEntity<Void> updateMe() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(new UserDto(user));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(new UserDto(user));
    }

    @GetMapping("/id/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user.getPosts());
    }
}
