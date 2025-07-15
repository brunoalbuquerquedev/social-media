package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<FollowDto>> findAll(@RequestParam(defaultValue = "0") int pageNumber,
                                                   @RequestParam(defaultValue = "10") int pageSize) {
        Page<FollowDto> page = followService.findAll(pageNumber, pageSize);
        return ResponseEntity.ok().body(page);
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
    public ResponseEntity<Page<UserDto>> getMyFollowers(@RequestParam(defaultValue = "0") int pageNumber,
                                                        @RequestParam(defaultValue = "10") int pageSize) {
        String loggedUserId = securityUtil.getLoggedUserId();
        List<FollowDto> followsList = followService.findAllById(loggedUserId, pageNumber, pageSize).toList();

        List<String> followsIdList = followsList.stream()
                .map(FollowDto::followerUserId)
                .toList();

        Page<UserDto> dtoList = userService.findAllById(followsIdList, pageNumber, pageSize);
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/followers/{id}")
    public ResponseEntity<Page<UserDto>> getFollowers(@PathVariable String id,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        UserDto dto = userService.findById(id);
        Page<UserDto> list = userService.findAllById(dto.followersIds(), page, size);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/following/{id}")
    public ResponseEntity<Page<UserDto>> getFollowing(@PathVariable String id,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        UserDto dto = userService.findById(id);
        Page<UserDto> list = userService.findAllById(dto.followingIds(), page, size);
        return ResponseEntity.ok(list);
    }
}
