package project.social.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.domain.Post;
import project.social.domain.User;
import project.social.dto.UserDto;
import project.social.services.UserService;
import project.social.util.JwtUtil;
import project.social.util.SecurityUtil;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SecurityUtil securityUtil;

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> findAll() {
        List<User> list = userService.findAll();

        List<UserDto> dtoList = list.stream()
                .map(UserDto::new)
                .toList();

        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me() {
        String id = securityUtil.getLoggedUserId();
        UserDto dto = new UserDto(userService.findById(id));
        return ResponseEntity.ok(dto);
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

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user.getPosts());
    }

    @PostMapping("/{id}/follow")
    public ResponseEntity<Void> follow(@PathVariable String id) {
        String followerId = securityUtil.getLoggedUserId();
        User user = userService.followUser(followerId, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}/unfollow")
    public ResponseEntity<Void> unfollow(@PathVariable String id) {
        String followerId = securityUtil.getLoggedUserId();
        User user = userService.unfollowUser(followerId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/followers")
    public ResponseEntity<List<UserDto>> getFollowersList(@PathVariable String id) {
        User user = userService.findById(id);
        List<User> list = user.getFollowers().stream()
                .map(userService::findById)
                .toList();

        List<UserDto> dtoList = list.stream()
                .map(UserDto::new)
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("{id}/following")
    public ResponseEntity<List<UserDto>> getFollowingList(@PathVariable String id) {
        User user = userService.findById(id);
        List<User> list = user.getFollowing().stream()
                .map(userService::findById)
                .toList();

        List<UserDto> dtoList = list.stream()
                .map(UserDto::new)
                .toList();

        return ResponseEntity.ok(dtoList);
    }
}
