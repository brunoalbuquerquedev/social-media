package project.social.resources;

import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.noContent().build();
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
                .map(Follow::getFollowedId)
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

    @GetMapping("/followed/{id}")
    public ResponseEntity<List<UserDto>> getFollowed(@PathVariable String id) {
        User user = userService.findById(id);
        List<User> following = userService.findAllById(user.getFollowedIds());

        List<UserDto> followingDtoList = following.stream()
                .map(UserDto::new)
                .toList();

        return ResponseEntity.ok(followingDtoList);
    }
}
