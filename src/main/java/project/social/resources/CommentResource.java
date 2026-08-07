package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.common.annotations.CurrentUser;
import project.social.common.dtos.domain.CommentDto;
import project.social.services.interfaces.ICommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentResource {

    private final ICommentService commentService;

    /**
     * Creates a new comment on a post.
     *
     * @param postId the ID of the post
     * @param content the comment content
     * @param currentUserId the ID of the current user
     * @return the created CommentDto
     */
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable String postId,
            @RequestParam String content,
            @CurrentUser String currentUserId) {
        CommentDto comment = commentService.createComment(postId, currentUserId, content);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    /**
     * Retrieves a comment by its ID.
     *
     * @param id the ID of the comment
     * @return the CommentDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable String id) {
        CommentDto comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    /**
     * Retrieves all comments for a specific post.
     *
     * @param postId the ID of the post
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of CommentDtos
     */
    @GetMapping("/post/{postId}")
    public ResponseEntity<Page<CommentDto>> getCommentsByPost(
            @PathVariable String postId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<CommentDto> comments = commentService.getCommentsByPostId(postId, pageNumber, pageSize);
        return ResponseEntity.ok(comments);
    }

    /**
     * Updates a comment.
     *
     * @param id the ID of the comment
     * @param content the new content
     * @param currentUserId the ID of the current user
     * @return the updated CommentDto
     */
    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable String id,
            @RequestParam String content,
            @CurrentUser String currentUserId) {
        CommentDto comment = commentService.updateComment(id, content);
        return ResponseEntity.ok(comment);
    }

    /**
     * Deletes a comment.
     *
     * @param id the ID of the comment
     * @param currentUserId the ID of the current user
     * @return no content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable String id,
            @CurrentUser String currentUserId) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
