package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.dto.domain.PostDto;
import project.social.services.PostService;
import project.social.util.SecurityUtil;
import project.social.util.UrlDecoder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostResource {

    private PostService postService;
    private SecurityUtil securityUtil;

    @GetMapping("/all")
    public ResponseEntity<Page<PostDto>> findAll(@RequestParam(defaultValue = "0") int pageNumber,
                                                 @RequestParam(defaultValue = "10") int pageSize) {
        Page<PostDto> page = postService.findAll(pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PostDto> findById(@PathVariable String id) {
        PostDto dto = postService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/id/{id}")
    public ResponseEntity<Void> likePost(@PathVariable String id) {
        String loggedUserId = securityUtil.getLoggedUserId();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> unlikePost(@PathVariable String id) {
        String loggedUserId = securityUtil.getLoggedUserId();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}/liked")
    public ResponseEntity<Page<PostDto>> getPostsLikedByUser(@PathVariable String id,
                                                             @RequestBody boolean hasUserLiked,
                                                             @RequestParam(defaultValue = "0") int pageNumber,
                                                             @RequestParam(defaultValue = "10") int pageSize) {
        Page<PostDto> page = postService.findAllByHasUserLiked(hasUserLiked, pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/title")
    public ResponseEntity<Page<PostDto>> getPostByTitle(@RequestParam(value = "text", defaultValue = "") String text,
                                                        @RequestParam(defaultValue = "0") int pageNumber,
                                                        @RequestParam(defaultValue = "10") int pageSize) {
        text = UrlDecoder.decodeParam(text);
        Page<PostDto> page = postService.findByTitle(text, pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }
}
