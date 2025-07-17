package project.social.services.interfaces;

import project.social.common.dtos.domain.FeedResponseDto;

public interface IFeedService {
    FeedResponseDto getFeed(String userId, int pageNumber, int pageSize);
}
