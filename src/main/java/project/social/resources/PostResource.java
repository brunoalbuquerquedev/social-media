package project.social.resources;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.common.annotations.CurrentUser;
import project.social.common.dtos.domain.PostDto;
import project.social.services.PostService;
import project.social.common.utils.SecurityUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostResource {

    private final PostService postService;
    private final SecurityUtils securityUtils;

    @GetMapping("/all")
    public ResponseEntity<Page<PostDto>> findAll(@RequestParam(defaultValue = "0") int pageNumber,
                                                 @RequestParam(defaultValue = "10") int pageSize) {
        Page<PostDto> page = postService.findAll(pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/post-id/{id}")
    public ResponseEntity<PostDto> findById(@PathVariable String id) {
        PostDto dto = postService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/post-id/{id}/liked")
    public ResponseEntity<Page<PostDto>> getPostsLikedByUser(@PathVariable String id,
                                                             @Valid @RequestBody boolean hasUserLiked,
                                                             @RequestParam(defaultValue = "0") int pageNumber,
                                                             @RequestParam(defaultValue = "10") int pageSize) {
        Page<PostDto> page = postService.findAllByAuthorIdAndByHasUserLiked(id, hasUserLiked, pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }


    @PostMapping()
    public ResponseEntity<Void> createPost(@Valid @RequestBody String content,
                                           @Valid @RequestBody String authorProfilePictureUrl,
                                           @Valid @RequestBody List<String> mediaUrl,
                                           @CurrentUser String currentUserId) {
        postService.createPost(currentUserId, content, authorProfilePictureUrl, mediaUrl);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/post-id/{id}")
    public ResponseEntity<Void> deletePost(@CurrentUser String currentUserId, @Valid @RequestParam String id) {
        postService.deletePost(currentUserId, id);
        return ResponseEntity.noContent().build();
    }
}
