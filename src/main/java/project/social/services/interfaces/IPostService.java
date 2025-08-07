package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.common.dtos.domain.PostDto;

import java.util.List;

public interface IPostService {
    /**
     * Finds a post by its ID.
     *
     * @param id the ID of the post
     * @return the post if found, otherwise null
     */
    PostDto findById(String id);

    /**
     * Finds all posts by the given user ID with pagination.
     *
     * @param authorId     the ID of the user
     * @param hasUserLiked indicates whether to filter posts by whether the user has liked them
     * @param pageNumber   the page number to retrieve
     * @param pageSize     the number of items per page
     * @return a paginated list of PostDto objects
     */
    Page<PostDto> findAllByAuthorIdAndByHasUserLiked(String authorId, boolean hasUserLiked, int pageNumber, int pageSize);

    /**
     * Finds all posts by the given user ID with pagination.
     *
     * @param id         the ID of the user
     * @param pageNumber the page number to retrieve
     * @param pageSize   the number of items per page
     * @return a paginated list of PostDto objects
     */
    Page<PostDto> findAllById(String id, int pageNumber, int pageSize);

    /**
     * Creates a new post.
     *
     * @param userId the ID of the user creating the post
     * @param content the content of the post
     * @param authorProfilePictureUrl the URL of the author's profile picture
     * @param mediaUrl a list of media URLs associated with the post
     */
    void createPost(String userId, String content, String authorProfilePictureUrl, List<String> mediaUrl);

    /**
     * Updates an existing post.
     *
     * @param userId the ID of the user updating the post
     * @param postId the ID of the post to be updated
     * @param content the new content of the post
     * @param mediaUrl a list of new media URLs associated with the post
     */
    void updatePost(String userId, String postId, String content, List<String> mediaUrl);

    /**
     * Deletes a post.
     *
     * @param userId the ID of the user deleting the post
     * @param postId the ID of the post to be deleted
     */
    void deletePost(String userId, String postId);
}
