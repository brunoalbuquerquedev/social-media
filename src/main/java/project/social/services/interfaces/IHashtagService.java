package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.common.dtos.domain.HashtagDto;

import java.util.List;

public interface IHashtagService {
    /**
     * Extracts and processes hashtags from content.
     *
     * @param content the content to extract hashtags from
     */
    void processHashtags(String content);

    /**
     * Searches for hashtags by tag name.
     *
     * @param tag the tag to search for
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of HashtagDtos
     */
    Page<HashtagDto> searchHashtags(String tag, int pageNumber, int pageSize);

    /**
     * Retrieves trending hashtags.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of trending HashtagDtos
     */
    Page<HashtagDto> getTrendingHashtags(int pageNumber, int pageSize);

    /**
     * Gets top 10 trending hashtags.
     *
     * @return a list of top 10 trending HashtagDtos
     */
    List<HashtagDto> getTopTrendingHashtags();

    /**
     * Gets a hashtag by its tag name.
     *
     * @param tag the tag name
     * @return the HashtagDto if found
     */
    HashtagDto getHashtagByTag(String tag);

    /**
     * Increments the usage count for a hashtag.
     *
     * @param tag the tag name
     */
    void incrementHashtagCount(String tag);
}
