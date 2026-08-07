package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.common.dtos.domain.HashtagDto;
import project.social.common.dtos.domain.PostDto;
import project.social.common.dtos.domain.SearchResultDto;
import project.social.common.dtos.domain.user.UserResponseDto;

public interface ISearchService {
    /**
     * Searches for users by username or full name.
     *
     * @param query the search query
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of UserResponseDtos
     */
    Page<UserResponseDto> searchUsers(String query, int pageNumber, int pageSize);

    /**
     * Searches for posts by content.
     *
     * @param query the search query
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of PostDtos
     */
    Page<PostDto> searchPosts(String query, int pageNumber, int pageSize);

    /**
     * Searches for hashtags by tag name.
     *
     * @param query the search query
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of HashtagDtos
     */
    Page<HashtagDto> searchHashtags(String query, int pageNumber, int pageSize);

    /**
     * Performs a global search across users, posts, and hashtags.
     *
     * @param query the search query
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a SearchResultDto containing results
     */
    SearchResultDto globalSearch(String query, int pageNumber, int pageSize);
}
