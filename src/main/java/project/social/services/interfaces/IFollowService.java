package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.dto.domain.FollowDto;

public interface IFollowService {
    Page<FollowDto> findAll(int page, int size);

    Page<FollowDto> findAllById(String id, int page, int size);

    void followUser(String requesterId, String targetId);

    void unfollowUser(String requesterId, String targetId);

    void deleteMutualFollowingByBlock(String requesterId, String targetId);
}
