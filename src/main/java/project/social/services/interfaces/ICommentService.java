package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.common.dtos.domain.CommentDto;

public interface ICommentService {
    /**
     * Creates a new comment.
     *
     * @param postId the ID of the post
     * @param authorId the ID of the comment author
     * @param content the comment content
     * @return the created CommentDto
     */
    CommentDto createComment(String postId, String authorId, String content);

    /**
     * Retrieves a comment by its ID.
     *
     * @param id the ID of the comment
     * @return the CommentDto if found
     */
    CommentDto getCommentById(String id);

    /**
     * Retrieves all comments for a specific post.
     *
     * @param postId the ID of the post
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of CommentDtos
     */
    Page<CommentDto> getCommentsByPostId(String postId, int pageNumber, int pageSize);

    /**
     * Updates a comment.
     *
     * @param id the ID of the comment
     * @param content the new content
     * @return the updated CommentDto
     */
    CommentDto updateComment(String id, String content);

    /**
     * Deletes a comment.
     *
     * @param id the ID of the comment
     */
    void deleteComment(String id);

    /**
     * Increments the likes count for a comment.
     *
     * @param id the ID of the comment
     */
    void incrementLikesCount(String id);

    /**
     * Decrements the likes count for a comment.
     *
     * @param id the ID of the comment
     */
    void decrementLikesCount(String id);
}
