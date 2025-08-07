package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.common.dtos.domain.LikeDto;

public interface ILikeService {
    /**
     * Finds a like by its ID.
     *
     * @param id the ID of the like
     * @return the LikeDto object if found, otherwise null
     */
    LikeDto findById(String id);

    /**
     * Finds all likes by the user ID with pagination.
     *
     * @param userId the ID of the user
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of LikeDto objects
     */
    Page<LikeDto> findAllByUserId(String userId, int pageNumber, int pageSize);

    /**
     * Finds all likes for a specific post with pagination.
     *
     * @param postId the ID of the post
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of LikeDto objects
     */
    Page<LikeDto> findAllByPostId(String postId, int pageNumber, int pageSize);

    /**
     * Likes a post by a user.
     *
     * @param userId the ID of the user liking the post
     * @param postId the ID of the post to be liked
     */
    void likePost(String userId, String postId);

    /**
     * Unlikes a post by a user.
     *
     * @param userId the ID of the user unliking the post
     * @param postId the ID of the post to be unliked
     */
    void unlikePost(String userId, String postId);
}
