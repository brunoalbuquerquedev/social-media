package project.social.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import project.social.domain.Post;
import project.social.domain.User;
import project.social.dto.UserDto;
import project.social.services.UserService;
import project.social.services.exceptions.InvalidTokenException;
import project.social.util.JwtUtil;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
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
    public ResponseEntity<Void> follow(@PathVariable("id") String followingId, @RequestHeader("Authorization") String authHeader) {
        String token = JwtUtil.extractAndValidateHeader(authHeader);
        String followerId = JwtUtil.validateAndGetUserId(token);
        User user = userService.followUser(followerId, followingId);
        return ResponseEntity.noContent().build();
    }

    //    @PostMapping
    public ResponseEntity<UserDto> insert(@RequestBody UserDto dto, UriComponentsBuilder builder) {
        User user = User.fromDto(dto);
        user = userService.insert(user);
        URI uri = builder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(user));
    }

    //    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDto dto, @PathVariable String id) {
        User user = User.fromDto(dto);
        user.setId(id);
        user = userService.update(user);
        return ResponseEntity.noContent().build();
    }
}
