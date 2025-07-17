package project.social.resources;

import jakarta.servlet.annotation.HttpConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.common.annotations.CurrentUser;
import project.social.common.dtos.domain.FollowDto;
import project.social.common.dtos.domain.user.UserDto;
import project.social.common.dtos.domain.user.UserResponseDto;
import project.social.services.FollowService;
import project.social.services.UserService;
import project.social.common.utils.SecurityUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/follows")
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
    public ResponseEntity<Void> follow(@PathVariable String id, @CurrentUser String currentUserId) {
        followService.followUser(currentUserId, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> unfollow(@PathVariable String id, @CurrentUser String currentUserId) {
        followService.unfollowUser(currentUserId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/followers/me")
    public ResponseEntity<Page<UserResponseDto>> getMyFollowers(@RequestParam(defaultValue = "0") int pageNumber,
                                                        @RequestParam(defaultValue = "10") int pageSize,
                                                        @CurrentUser String currentUserId) {
        List<FollowDto> followsList = followService.findAllById(currentUserId, pageNumber, pageSize).toList();

        List<String> followsIdList = followsList.stream()
                .map(FollowDto::followerUserId)
                .toList();

        Page<UserResponseDto> dtoList = userService.findAllById(followsIdList, pageNumber, pageSize);
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/followers/{id}")
    public ResponseEntity<Page<UserResponseDto>> getFollowers(@PathVariable String id,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        UserResponseDto dto = userService.findById(id);
        Page<UserResponseDto> list = userService.findAllById(dto.followersIds(), page, size);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/following/{id}")
    public ResponseEntity<Page<UserResponseDto>> getFollowing(@PathVariable String id,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        UserResponseDto dto = userService.findById(id);
        Page<UserResponseDto> list = userService.findAllById(dto.followingIds(), page, size);
        return ResponseEntity.ok(list);
    }
}
