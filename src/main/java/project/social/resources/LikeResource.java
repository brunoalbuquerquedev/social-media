package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.dto.domain.LikeDto;
import project.social.services.LikeService;
import project.social.util.SecurityUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/like")
public class LikeResource {

    private final LikeService likeService;
    private final SecurityUtils securityUtils;

    @GetMapping("/all")
    public ResponseEntity<Page<LikeDto>> findAll(@RequestParam(defaultValue = "0") int pageNumber,
                                                 @RequestParam(defaultValue = "10") int pageSize) {
        Page<LikeDto> page = likeService.findAll(pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/post-id/{id}")
    public ResponseEntity<Page<LikeDto>> getPostLikes(@PathVariable String id,
                                                      @RequestParam(defaultValue = "0") int pageNumber,
                                                      @RequestParam(defaultValue = "10") int pageSize) {
        Page<LikeDto> page = likeService.findAllByPostId(id, pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/user-id/{id}/last-likes")
    public ResponseEntity<Page<LikeDto>> getUserLastLikes(@PathVariable String id,
                                                          @RequestParam(defaultValue = "0") int pageNumber,
                                                          @RequestParam(defaultValue = "10") int pageSize) {
        Page<LikeDto> page = likeService.findAllByUserId(id, pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/post-id/{id}")
    public ResponseEntity<Void> likePost(@PathVariable String id) {
        String loggedUserId = securityUtils.getLoggedUserId();
        likeService.likePost(loggedUserId, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/post-id/{id}")
    public ResponseEntity<Void> unlikePost(@PathVariable String id) {
        String loggedUserId = securityUtils.getLoggedUserId();
        likeService.unlikePost(loggedUserId, id);
        return ResponseEntity.noContent().build();
    }
}
