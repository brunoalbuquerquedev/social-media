package project.social.services.interfaces;

import project.social.dto.domain.FollowDto;

import java.util.List;

public interface IFollowService {
    List<FollowDto> findAll();

    List<FollowDto> findById(String id);

    void followUser(String requesterId, String targetId);

    void unfollowUser(String requesterId, String targetId);

    void deleteMutualFollowingByBlock(String requesterId, String targetId);
}
