package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.domain.Post;
import project.social.dto.domain.PostDto;
import project.social.util.SecurityUtil;
import project.social.util.UrlDecoder;
import project.social.services.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostResource {

    private PostService postService;
    private SecurityUtil securityUtil;

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> findAll() {
        List<PostDto> list = postService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PostDto> findById(@PathVariable String id) {
        PostDto dto = postService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/id/{id}")
    public ResponseEntity<Void> likePost(@PathVariable String id) {
        String userId = securityUtil.getLoggedUserId();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> unlikePost(@PathVariable String id) {
        String userId = securityUtil.getLoggedUserId();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}/liked")
    public ResponseEntity<List<PostDto>> getPostsLikedByUser(@PathVariable String id) {
        List<PostDto> list = postService.findByHasUserLiked();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/title")
    public ResponseEntity<List<PostDto>> getPostByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = UrlDecoder.decodeParam(text);
        List<PostDto> list = postService.findByTitle(text);
        return ResponseEntity.ok(list);
    }
}
