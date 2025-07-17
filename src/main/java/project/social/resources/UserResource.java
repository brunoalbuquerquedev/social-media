package project.social.resources;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.common.annotations.CurrentUser;
import project.social.common.dtos.domain.PostDto;
import project.social.common.dtos.domain.user.UserDto;
import project.social.common.dtos.domain.user.UserResponseDto;
import project.social.common.dtos.domain.user.UserUpdateDto;
import project.social.common.utils.JwtUtils;
import project.social.common.utils.SecurityUtils;
import project.social.services.FollowService;
import project.social.services.PostService;
import project.social.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
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
    public ResponseEntity<UserResponseDto> getMe(@CurrentUser String currentUserId) {
        UserResponseDto dto = userService.findById(currentUserId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/me/update")
    public ResponseEntity<Void> updateMe(@Valid @RequestBody UserUpdateDto request, @CurrentUser String currentUserId) {
        userService.updateUser(currentUserId, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable String id) {
        UserResponseDto dto = userService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDto> findByUsername(@Valid @PathVariable String username) {
        UserResponseDto dto = userService.findByUsername(username);
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
