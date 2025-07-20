package project.social.services.interfaces;

import project.social.dto.domain.FeedDto;

public interface IFeedService {
    /**
     * Retrieves the feed for a user based on their ID, with pagination support.
     *
     * @param userId the ID of the user whose feed is to be retrieved
     * @param pageNumber the page number for pagination
     * @param pageSize the number of items per page
     * @return a FeedDto containing the user's feed data
     */
    FeedDto getFeed(String userId, int pageNumber, int pageSize);
}
