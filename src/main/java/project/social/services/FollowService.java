package project.social.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.social.domain.Follow;
import project.social.domain.User;
import project.social.domain.enums.FollowStatus;
import project.social.repositories.FollowRepository;
import project.social.repositories.UserRepository;
import project.social.services.exceptions.IllegalFollowingArgumentException;
import project.social.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class FollowService {

    @Autowired
    private final FollowRepository followRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserService userService;

    public FollowService(FollowRepository followRepository, UserRepository userRepository, UserService userService) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void followUser(String followerId, String followingId) {
        if (followerId.equals(followingId))
            throw new IllegalFollowingArgumentException("You cannot follow yourself.");

        User follower = userService.findById(followerId);
        User following = userService.findById(followingId);

        List<String> followersIdList = follower.getFollowersIds();
        List<String> followingsIdList = following.getFollowingIds();

        Follow.Builder followBuilder = Follow.builder();

        if (!followersIdList.contains(following.getId()))
            followBuilder.followingId(followingId);

        if (!followingsIdList.contains(follower.getId()))
            followBuilder.followerId(followerId);

        followBuilder.status(FollowStatus.FOLLOWING);
        Follow follow = followBuilder.build();

        followRepository.save(follow);
        follower.getFollowersIds().add(follow.getId());
        following.getFollowingIds().add(follow.getId());
    }

    public void unfollowUser(String followerId, String unfollowingId) {
        if (followerId.equals(unfollowingId))
            throw new IllegalFollowingArgumentException("You cannot unfollow yourself.");

        User follower = userService.findById(followerId);
        User unfollowing = userService.findById(unfollowingId);

        Optional<Follow> optionalFollow = followRepository.findByFollowerIdAndFollowingId(followerId, unfollowingId);

        if (optionalFollow.isEmpty())
            throw new ObjectNotFoundException("Follow relationship not found.");

        Follow follow = optionalFollow.get();
        followRepository.delete(follow);

        follower.getFollowingIds().remove(unfollowingId);
        unfollowing.getFollowersIds().remove(followerId);
        userRepository.save(follower);
        userRepository.save(unfollowing);
    }
}
