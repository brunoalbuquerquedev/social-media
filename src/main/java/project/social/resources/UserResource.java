package project.social.resources;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.dto.domain.PostDto;
import project.social.dto.domain.UserDto;
import project.social.services.FollowService;
import project.social.services.PostService;
import project.social.services.UserService;
import project.social.util.JwtUtils;
import project.social.util.SecurityUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserResource {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final SecurityUtils securityUtils;
    private final FollowService followService;
    private final PostService postService;

    @GetMapping("/all")
    public ResponseEntity<Page<UserDto>> findAll(@RequestParam(defaultValue = "0") int pageNumber,
                                                 @RequestParam(defaultValue = "10") int pageSize) {
        Page<UserDto> page = userService.findAll(pageNumber, pageSize);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe() {
        String loggedUserId = securityUtils.getLoggedUserId();
        UserDto dto = userService.findById(loggedUserId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/me/update")
    public ResponseEntity<Void> updateMe(@Valid @RequestBody UserDto request) {
        String loggedUserId = securityUtils.getLoggedUserId();
        userService.updateUser(loggedUserId, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        UserDto dto = userService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> findByUsername(@Valid @PathVariable String username) {
        UserDto dto = userService.findByUsername(username);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/id/{id}/posts")
    public ResponseEntity<Page<PostDto>> findPosts(@Valid @PathVariable String id,
                                                   @RequestParam(defaultValue = "0") int pageNumber,
                                                   @RequestParam(defaultValue = "10") int pageSize) {
        Page<PostDto> page = postService.findAllById(id, pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }
}
