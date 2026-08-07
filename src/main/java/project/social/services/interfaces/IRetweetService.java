package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.common.dtos.domain.RetweetDto;

public interface IRetweetService {
    /**
     * Creates a new retweet.
     *
     * @param postId the ID of the post
     * @param userId the ID of the user
     * @return the created RetweetDto
     */
    RetweetDto createRetweet(String postId, String userId);

    /**
     * Deletes a retweet.
     *
     * @param postId the ID of the post
     * @param userId the ID of the user
     */
    void deleteRetweet(String postId, String userId);

    /**
     * Retrieves all retweets for a specific post.
     *
     * @param postId the ID of the post
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of RetweetDtos
     */
    Page<RetweetDto> getRetweetsByPostId(String postId, int pageNumber, int pageSize);

    /**
     * Gets the count of retweets for a specific post.
     *
     * @param postId the ID of the post
     * @return the count of retweets
     */
    long getRetweetCount(String postId);

    /**
     * Checks if a user has retweeted a post.
     *
     * @param postId the ID of the post
     * @param userId the ID of the user
     * @return true if the user has retweeted the post, false otherwise
     */
    boolean hasUserRetweeted(String postId, String userId);
}
