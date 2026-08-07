package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.common.dtos.domain.HashtagDto;
import project.social.common.dtos.domain.PostDto;
import project.social.common.dtos.domain.SearchResultDto;
import project.social.common.dtos.domain.user.UserResponseDto;
import project.social.services.interfaces.ISearchService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchResource {

    private final ISearchService searchService;

    /**
     * Searches for users by username or full name.
     *
     * @param query the search query
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of UserResponseDtos
     */
    @GetMapping("/users")
    public ResponseEntity<Page<UserResponseDto>> searchUsers(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<UserResponseDto> users = searchService.searchUsers(query, pageNumber, pageSize);
        return ResponseEntity.ok(users);
    }

    /**
     * Searches for posts by content.
     *
     * @param query the search query
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of PostDtos
     */
    @GetMapping("/posts")
    public ResponseEntity<Page<PostDto>> searchPosts(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<PostDto> posts = searchService.searchPosts(query, pageNumber, pageSize);
        return ResponseEntity.ok(posts);
    }

    /**
     * Searches for hashtags by tag name.
     *
     * @param query the search query
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of HashtagDtos
     */
    @GetMapping("/hashtags")
    public ResponseEntity<Page<HashtagDto>> searchHashtags(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<HashtagDto> hashtags = searchService.searchHashtags(query, pageNumber, pageSize);
        return ResponseEntity.ok(hashtags);
    }

    /**
     * Performs a global search across users, posts, and hashtags.
     *
     * @param query the search query
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a SearchResultDto containing results
     */
    @GetMapping
    public ResponseEntity<SearchResultDto> globalSearch(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        SearchResultDto result = searchService.globalSearch(query, pageNumber, pageSize);
        return ResponseEntity.ok(result);
    }
}
