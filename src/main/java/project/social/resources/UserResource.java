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
@RequestMapping("/api/v1/users")
public class UserResource {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final SecurityUtils securityUtils;
    private final FollowService followService;
    private final PostService postService;

    /**
     * Retrieves all users with pagination.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize   the number of items per page
     * @return a paginated list of UserDto
     */
    @GetMapping("/all")
    public ResponseEntity<Page<UserDto>> findAll(@RequestParam(defaultValue = "0") int pageNumber,
                                                 @RequestParam(defaultValue = "10") int pageSize) {
        Page<UserDto> page = userService.findAll(pageNumber, pageSize);
        return ResponseEntity.ok().body(page);
    }

    /**
     * Returns the data of the currently authenticated user.
     *
     * <p>Obtains the logged user's ID using the security utility and
     * fetches the corresponding user information.</p>
     *
     * @return {@link ResponseEntity} containing the {@link UserDto} of the logged user.
     */
    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe() {
        String loggedUserId = securityUtils.getLoggedUserId();
        UserDto dto = userService.findById(loggedUserId);
        return ResponseEntity.ok(dto);
    }

    /**
     * Updates the data of the currently authenticated user.
     *
     * <p>Obtains the logged user's ID using the security utility and updates
     * the user's information with the provided data.</p>
     *
     * @param request The {@link UserDto} containing the updated user data.
     * @return {@link ResponseEntity} with no content if the update is successful.
     */
    @PutMapping("/me/update")
    public ResponseEntity<Void> updateMe(@Valid @RequestBody UserDto request) {
        String loggedUserId = securityUtils.getLoggedUserId();
        userService.updateUser(loggedUserId, request);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves a user's data by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return {@link ResponseEntity} containing the {@link UserDto} of the user.
     */
    @GetMapping("/user-id/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        UserDto dto = userService.findById(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * Retrieves a user's data by their username.
     *
     * @param username The username of the user to retrieve.
     * @return {@link ResponseEntity} containing the {@link UserDto} of the user.
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> findByUsername(@Valid @PathVariable String username) {
        UserDto dto = userService.findByUsername(username);
        return ResponseEntity.ok(dto);
    }

    /**
     * Retrieves a paginated list of posts for a specific user.
     *
     * @param id The ID of the user whose posts are to be retrieved.
     * @param pageNumber The page number to retrieve (default is 0).
     * @param pageSize The number of items per page (default is 10).
     * @return {@link ResponseEntity} containing a {@link Page} of {@link PostDto}.
     */
    @GetMapping("/user-id/{id}/posts")
    public ResponseEntity<Page<PostDto>> findPosts(@Valid @PathVariable String id,
                                                   @RequestParam(defaultValue = "0") int pageNumber,
                                                   @RequestParam(defaultValue = "10") int pageSize) {
        Page<PostDto> page = postService.findAllById(id, pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }
}
