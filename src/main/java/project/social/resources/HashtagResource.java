package project.social.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.social.common.dtos.domain.HashtagDto;
import project.social.services.interfaces.IHashtagService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hashtags")
public class HashtagResource {

    private final IHashtagService hashtagService;

    /**
     * Searches for hashtags by tag name.
     *
     * @param tag the tag to search for
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of HashtagDtos
     */
    @GetMapping("/search")
    public ResponseEntity<Page<HashtagDto>> searchHashtags(
            @RequestParam String tag,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<HashtagDto> hashtags = hashtagService.searchHashtags(tag, pageNumber, pageSize);
        return ResponseEntity.ok(hashtags);
    }

    /**
     * Retrieves trending hashtags.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of trending HashtagDtos
     */
    @GetMapping("/trending")
    public ResponseEntity<Page<HashtagDto>> getTrendingHashtags(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<HashtagDto> hashtags = hashtagService.getTrendingHashtags(pageNumber, pageSize);
        return ResponseEntity.ok(hashtags);
    }

    /**
     * Gets top 10 trending hashtags.
     *
     * @return a list of top 10 trending HashtagDtos
     */
    @GetMapping("/trending/top")
    public ResponseEntity<List<HashtagDto>> getTopTrendingHashtags() {
        List<HashtagDto> hashtags = hashtagService.getTopTrendingHashtags();
        return ResponseEntity.ok(hashtags);
    }

    /**
     * Gets a hashtag by its tag name.
     *
     * @param tag the tag name
     * @return the HashtagDto
     */
    @GetMapping("/{tag}")
    public ResponseEntity<HashtagDto> getHashtagByTag(@PathVariable String tag) {
        HashtagDto hashtag = hashtagService.getHashtagByTag(tag);
        return ResponseEntity.ok(hashtag);
    }
}
