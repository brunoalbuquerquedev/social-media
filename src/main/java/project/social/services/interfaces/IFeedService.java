package project.social.services.interfaces;

import project.social.dto.domain.FeedDto;

public interface IFeedService {
    FeedDto getFeed(String userId, int pageNumber, int pageSize);
}
