package project.social.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.social.domain.Follow;
import project.social.domain.User;
import project.social.domain.enums.FollowStatus;
import project.social.repositories.FollowRepository;
import project.social.repositories.UserRepository;
import project.social.services.exceptions.FollowAlreadyExistsException;
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

    public List<Follow> findAll() {
        return followRepository.findAll();
    }

    public List<Follow> findById(String id) {
        return followRepository.findByFollowerId(id);
    }

    public void followUser(String followerUserId, String followedUserId) {
        if (followerUserId.equals(followedUserId))
            throw new IllegalFollowingArgumentException("You cannot follow yourself.");

        User followerUser = userService.findById(followerUserId);
        User followedUser = userService.findById(followedUserId);

        Optional<Follow> optionalFollow = followRepository.findByFollowerIdAndFollowedId(followerUserId, followedUserId);

        if (optionalFollow.isPresent())
            throw new FollowAlreadyExistsException("The user is already following.");

        Follow.Builder followBuilder = Follow.builder();

        followBuilder.followerId(followerUserId);
        followBuilder.followedId(followedUserId);
        followBuilder.status(FollowStatus.FOLLOWING);
        Follow follow = followBuilder.build();

        followRepository.save(follow);

        followerUser.getFollowersIds().add(follow.getId());
        followedUser.getFollowedIds().add(follow.getId());
    }

    public void unfollowUser(String followerId, String unfollowingId) {
        if (followerId.equals(unfollowingId))
            throw new IllegalFollowingArgumentException("You cannot unfollow yourself.");

        User follower = userService.findById(followerId);
        User unfollowing = userService.findById(unfollowingId);

        Optional<Follow> optionalFollow = followRepository.findByFollowerIdAndFollowedId(followerId, unfollowingId);

        if (optionalFollow.isEmpty())
            throw new ObjectNotFoundException("Follow relationship not found.");

        Follow follow = optionalFollow.get();
        followRepository.delete(follow);

        follower.getFollowedIds().remove(unfollowingId);
        unfollowing.getFollowersIds().remove(followerId);
        userRepository.save(follower);
        userRepository.save(unfollowing);
    }
}
