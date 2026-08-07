package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.common.dtos.domain.HashtagDto;
import project.social.common.dtos.domain.PostDto;
import project.social.services.interfaces.ITrendingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trending")
public class TrendingResource {

    private final ITrendingService trendingService;

    /**
     * Retrieves trending posts.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of trending PostDtos
     */
    @GetMapping("/posts")
    public ResponseEntity<Page<PostDto>> getTrendingPosts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<PostDto> posts = trendingService.getTrendingPosts(pageNumber, pageSize);
        return ResponseEntity.ok(posts);
    }

    /**
     * Retrieves top trending posts.
     *
     * @param limit the number of top posts to retrieve
     * @return a list of top trending PostDtos
     */
    @GetMapping("/posts/top")
    public ResponseEntity<List<PostDto>> getTopTrendingPosts(
            @RequestParam(defaultValue = "10") int limit) {
        List<PostDto> posts = trendingService.getTopTrendingPosts(limit);
        return ResponseEntity.ok(posts);
    }

    /**
     * Retrieves trending hashtags.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of trending HashtagDtos
     */
    @GetMapping("/hashtags")
    public ResponseEntity<Page<HashtagDto>> getTrendingHashtags(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<HashtagDto> hashtags = trendingService.getTrendingHashtags(pageNumber, pageSize);
        return ResponseEntity.ok(hashtags);
    }

    /**
     * Retrieves top trending hashtags.
     *
     * @param limit the number of top hashtags to retrieve
     * @return a list of top trending HashtagDtos
     */
    @GetMapping("/hashtags/top")
    public ResponseEntity<List<HashtagDto>> getTopTrendingHashtags(
            @RequestParam(defaultValue = "10") int limit) {
        List<HashtagDto> hashtags = trendingService.getTopTrendingHashtags(limit);
        return ResponseEntity.ok(hashtags);
    }
}
