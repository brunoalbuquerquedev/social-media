package project.social.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.domain.Post;
import project.social.util.SecurityUtil;
import project.social.util.UrlDecoder;
import project.social.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @Autowired
    private SecurityUtil securityUtil;

    @GetMapping("/list")
    public ResponseEntity<List<Post>> findAll() {
        List<Post> list = postService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Void> like(@PathVariable String id) {
        String userId = securityUtil.getLoggedUserId();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/unlike")
    public ResponseEntity<Void> unlike(@PathVariable String id) {
        String userId = securityUtil.getLoggedUserId();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/likes")
    public ResponseEntity<Void> likes(@PathVariable String id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/title")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = UrlDecoder.decodeParam(text);
        List<Post> list = postService.findByTitle(text);
        return ResponseEntity.ok(list);
    }
}
