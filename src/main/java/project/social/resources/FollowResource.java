package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.dto.domain.FollowDto;
import project.social.dto.domain.UserDto;
import project.social.services.FollowService;
import project.social.services.UserService;
import project.social.util.SecurityUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follows")
public class FollowResource {

    private final UserService userService;
    private final FollowService followService;
    private final SecurityUtils securityUtils;

    @GetMapping("/all")
    public ResponseEntity<List<FollowDto>> findAll() {
        List<FollowDto> list = followService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/id/{id}")
    public ResponseEntity<Void> follow(@PathVariable String id) {
        String loggedUserId = securityUtils.getLoggedUserId();
        followService.followUser(loggedUserId, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> unfollow(@PathVariable String id) {
        String loggedUserId = securityUtils.getLoggedUserId();
        followService.unfollowUser(loggedUserId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/followers/me")
    public ResponseEntity<List<UserDto>> getMyFollowers() {
        String loggedUserId = securityUtils.getLoggedUserId();
        List<FollowDto> followsList = followService.findById(loggedUserId);

        List<String> list = followsList.stream()
                .map(FollowDto::followerUserId)
                .toList();

        List<UserDto> dtoList = userService.findAllById(list);
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/followers/{id}")
    public ResponseEntity<List<UserDto>> getFollowers(@PathVariable String id) {
        UserDto dto = userService.findById(id);
        List<UserDto> list = userService.findAllById(dto.followersIds());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/following/{id}")
    public ResponseEntity<List<UserDto>> getFollowing(@PathVariable String id) {
        UserDto dto = userService.findById(id);
        List<UserDto> list = userService.findAllById(dto.followingIds());
        return ResponseEntity.ok(list);
    }
}
