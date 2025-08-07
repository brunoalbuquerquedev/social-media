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
@RequestMapping("/api/v1/follow")
public class FollowResource {

    private final UserService userService;
    private final FollowService followService;
    private final SecurityUtils securityUtils;

    /**
     * Retrieves all follows with pagination.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize   the number of items per page
     * @return a paginated list of FollowDto
     */
    @GetMapping("/all")
    public ResponseEntity<Page<FollowDto>> findAll(@RequestParam(defaultValue = "0") int pageNumber,
                                                   @RequestParam(defaultValue = "10") int pageSize) {
        Page<FollowDto> page = followService.findAll(pageNumber, pageSize);
        return ResponseEntity.ok().body(page);
    }

    /**
     * Follows a user by their ID.
     *
     * @param id the ID of the user to follow
     * @return a response indicating the result of the follow operation
     */
    @PostMapping("/user-id/{id}")
    public ResponseEntity<Void> follow(@PathVariable String id, @CurrentUser String currentUserId) {
        followService.followUser(currentUserId, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Unfollows a user by their ID.
     *
     * @param id the ID of the user to unfollow
     * @return a response indicating the result of the unfollow operation
     */
    @DeleteMapping("/user-id/{id}")
    public ResponseEntity<Void> unfollow(@PathVariable String id, @CurrentUser String currentUserId) {
        followService.unfollowUser(currentUserId, id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves the followers of the logged-in user with pagination.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize   the number of items per page
     * @return a paginated list of UserDto representing the followers
     */
    @GetMapping("/me/all-followers")
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

    /**
     * Retrieves the users that the logged-in user is following with pagination.
     *
     * @param pageNumber the pageNumber number to retrieve
     * @param pageSize   the number of items per pageNumber
     * @return a paginated list of UserDto representing the following users
     */
    @GetMapping("/user-id/{id}/all-followers")
    public ResponseEntity<Page<UserResponseDto>> getFollowers(@PathVariable String id,
                                                              @RequestParam(defaultValue = "0") int pageNumber,
                                                              @RequestParam(defaultValue = "10") int pageSize) {
        UserResponseDto dto = userService.findById(id);
        Page<UserResponseDto> list = userService.findAllById(dto.followersIds(), pageNumber, pageSize);
        return ResponseEntity.ok(list);
    }

    /**
     * Retrieves the users that the logged-in user is following with pagination.
     *
     * @param id   the ID of the user whose following list is to be retrieved
     * @param pageNumber the pageNumber number to retrieve
     * @param pageSize the number of items per pageNumber
     * @return a paginated list of UserDto representing the following users
     */
    @GetMapping("/user-id/{id}/all-following")
    public ResponseEntity<Page<UserResponseDto>> getFollowing(@PathVariable String id,
                                                              @RequestParam(defaultValue = "0") int pageNumber,
                                                              @RequestParam(defaultValue = "10") int pageSize) {
        UserResponseDto dto = userService.findById(id);
        Page<UserResponseDto> list = userService.findAllById(dto.followingIds(), pageNumber, pageSize);
        return ResponseEntity.ok(list);
    }
}
