package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.domain.Follow;
import project.social.domain.User;
import project.social.dto.domain.FollowDto;
import project.social.dto.domain.UserDto;
import project.social.services.FollowService;
import project.social.services.UserService;
import project.social.util.SecurityUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follows")
public class FollowResource {

    private final UserService userService;
    private final FollowService followService;
    private final SecurityUtil securityUtil;

    @GetMapping("/all")
    public ResponseEntity<List<FollowDto>> findAll() {
        List<Follow> followList = followService.findAll();

        List<FollowDto> dtoList = followList.stream()
                .map(FollowDto::new)
                .toList();

        return ResponseEntity.ok().body(dtoList);
    }

    @PostMapping("/id/{id}")
    public ResponseEntity<Void> follow(@PathVariable String id) {
        String loggedUserId = securityUtil.getLoggedUserId();
        followService.followUser(loggedUserId, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> unfollow(@PathVariable String id) {
        String loggedUserId = securityUtil.getLoggedUserId();
        followService.unfollowUser(loggedUserId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/followers/me")
    public ResponseEntity<List<UserDto>> getMeFollowers() {
        String loggedUserId = securityUtil.getLoggedUserId();
        List<Follow> followsList = followService.findById(loggedUserId);

        List<String> list = followsList.stream()
                .map(Follow::getFollowingUserId)
                .toList();

        List<UserDto> dtoList = userService.findAllById(list).stream()
                .map(UserDto::new)
                .toList();

        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/followers/{id}")
    public ResponseEntity<List<UserDto>> getFollowers(@PathVariable String id) {
        User user = userService.findById(id);
        List<User> followers = userService.findAllById(user.getFollowersIds());

        List<UserDto> followersDtoList = followers.stream()
                .map(UserDto::new)
                .toList();

        return ResponseEntity.ok(followersDtoList);
    }

    @GetMapping("/following/{id}")
    public ResponseEntity<List<UserDto>> getFollowing(@PathVariable String id) {
        User user = userService.findById(id);
        List<User> following = userService.findAllById(user.getFollowingIds());

        List<UserDto> followingDtoList = following.stream()
                .map(UserDto::new)
                .toList();

        return ResponseEntity.ok(followingDtoList);
    }
}
