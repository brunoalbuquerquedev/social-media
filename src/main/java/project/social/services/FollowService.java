package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.social.domain.Follow;
import project.social.domain.User;
import project.social.domain.enums.FollowStatus;
import project.social.exceptions.follow.FollowAlreadyExistsException;
import project.social.exceptions.follow.InvalidFollowRequestException;
import project.social.repositories.FollowRepository;
import project.social.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public List<Follow> findAll() {
        return followRepository.findAll();
    }

    public List<Follow> findById(String id) {
        return followRepository.findByRequesterId(id);
    }

    public void followUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidFollowRequestException("You cannot follow yourself.");

        User requesterUser = userService.findById(requesterId);
        User targetUser = userService.findById(targetId);

        Optional<Follow> optionalFollow0 = followRepository
                .findByRequesterIdAndTargetId(requesterId, targetId);

        if (optionalFollow0.isPresent())
            throw new FollowAlreadyExistsException("The user is already followed.");

        Optional<Follow> optionalFollow1 = followRepository
                .findByRequesterIdAndTargetId(targetId, requesterId);

        FollowStatus status = FollowStatus.FOLLOWING;

        if (optionalFollow1.isPresent())
            status = FollowStatus.MUTUAL_FOLLOWING;

        Follow follow = Follow.builder()
                .followerUserId(requesterId)
                .followingUserId(targetId)
                .status(status)
                .build();

        followRepository.save(follow);
        requesterUser.getFollowersIds().add(follow.getId());
        targetUser.getFollowingIds().add(follow.getId());
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
    }

    public void unfollowUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidFollowRequestException("You cannot unfollow yourself.");

        User requester = userService.findById(requesterId);
        User target = userService.findById(targetId);

        Optional<Follow> optionalFollow = followRepository
                .findByRequesterIdAndTargetId(requesterId, targetId);

        optionalFollow.ifPresent(followRepository::delete);
        optionalFollow.map(Follow::getFollowingUserId).ifPresent(id -> requester.getFollowersIds().remove(id));
        optionalFollow.map(Follow::getFollowerUserId).ifPresent(id -> target.getFollowingIds().remove(id));
        userRepository.saveAll(Arrays.asList(requester, target));
    }

    public void deleteMutualFollowingByBlock(String requesterId, String targetId) {
        Optional<Follow> optionalFollow0 = followRepository.findByRequesterIdAndTargetId(requesterId, targetId);
        Optional<Follow> optionalFollow1 = followRepository.findByRequesterIdAndTargetId(targetId, requesterId);

        List<Follow> list = new ArrayList<>();
        optionalFollow0.ifPresent(list::add);
        optionalFollow1.ifPresent(list::add);

        if (!list.isEmpty())
            followRepository.deleteAll(list);
    }
}
