package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.common.dtos.domain.BookmarkDto;

public interface IBookmarkService {
    /**
     * Creates a new bookmark.
     *
     * @param postId the ID of the post
     * @param userId the ID of the user
     * @return the created BookmarkDto
     */
    BookmarkDto createBookmark(String postId, String userId);

    /**
     * Deletes a bookmark.
     *
     * @param postId the ID of the post
     * @param userId the ID of the user
     */
    void deleteBookmark(String postId, String userId);

    /**
     * Retrieves all bookmarks for a user.
     *
     * @param userId the ID of the user
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of BookmarkDtos
     */
    Page<BookmarkDto> getUserBookmarks(String userId, int pageNumber, int pageSize);

    /**
     * Gets the count of bookmarks for a specific post.
     *
     * @param postId the ID of the post
     * @return the count of bookmarks
     */
    long getBookmarkCount(String postId);

    /**
     * Checks if a user has bookmarked a post.
     *
     * @param postId the ID of the post
     * @param userId the ID of the user
     * @return true if the user has bookmarked the post, false otherwise
     */
    boolean hasUserBookmarked(String postId, String userId);
}
