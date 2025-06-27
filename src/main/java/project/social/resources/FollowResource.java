package project.social.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.domain.User;
import project.social.dto.domain.UserDto;
import project.social.services.FollowService;
import project.social.services.UserService;
import project.social.util.SecurityUtil;

import java.util.List;

@RestController
@RequestMapping("/follows")
public class FollowResource {

    @Autowired
    private final UserService userService;

    @Autowired
    private final FollowService followService;

    @Autowired
    private final SecurityUtil securityUtil;

    public FollowResource(UserService userService, FollowService followService, SecurityUtil securityUtil) {
        this.userService = userService;
        this.followService = followService;
        this.securityUtil = securityUtil;
    }

    @PostMapping("/follow/{id}")
    public ResponseEntity<Void> follow(@PathVariable String id) {
        String loggedUserId = securityUtil.getLoggedUserId();
        followService.followUser(loggedUserId, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/unfollow/{id}")
    public ResponseEntity<Void> unfollow(@PathVariable String id) {
        String loggedUserId = securityUtil.getLoggedUserId();
        followService.unfollowUser(loggedUserId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/followers/{id}")
    public ResponseEntity<List<UserDto>> getFollowersList(@PathVariable String id) {
        User user = userService.findById(id);
        List<User> followers = userService.findAllById(user.getFollowersIds());

        List<UserDto> followersDtoList = followers.stream()
                .map(UserDto::new)
                .toList();

        return ResponseEntity.ok(followersDtoList);
    }

    @GetMapping("/following/{id}")
    public ResponseEntity<List<UserDto>> getFollowingList(@PathVariable String id) {
        User user = userService.findById(id);
        List<User> following = userService.findAllById(user.getFollowingIds());

        List<UserDto> followingDtoList = following.stream()
                .map(UserDto::new)
                .toList();

        return ResponseEntity.ok(followingDtoList);
    }
}
