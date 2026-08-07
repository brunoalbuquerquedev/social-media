package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.common.dtos.domain.HashtagDto;
import project.social.common.dtos.domain.PostDto;
import project.social.common.dtos.domain.TrendingDto;

import java.util.List;

public interface ITrendingService {
    /**
     * Retrieves trending posts based on likes and comments in the last 7 days.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of trending PostDtos
     */
    Page<PostDto> getTrendingPosts(int pageNumber, int pageSize);

    /**
     * Retrieves top trending posts.
     *
     * @param limit the number of top posts to retrieve
     * @return a list of top trending PostDtos
     */
    List<PostDto> getTopTrendingPosts(int limit);

    /**
     * Retrieves trending hashtags based on usage count.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of trending HashtagDtos
     */
    Page<HashtagDto> getTrendingHashtags(int pageNumber, int pageSize);

    /**
     * Retrieves top trending hashtags.
     *
     * @param limit the number of top hashtags to retrieve
     * @return a list of top trending HashtagDtos
     */
    List<HashtagDto> getTopTrendingHashtags(int limit);
}
