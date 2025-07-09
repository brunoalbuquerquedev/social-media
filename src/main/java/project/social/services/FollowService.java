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

import java.util.Arrays;
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
            throw new FollowAlreadyExistsException("The user is already followed.");

        Follow follow = Follow.builder()
                .followerId(followerUserId)
                .followedId(followedUserId)
                .status(FollowStatus.FOLLOWING)
                .build();

        followRepository.save(follow);

        followerUser.getFollowersIds().add(follow.getId());
        followedUser.getFollowedIds().add(follow.getId());
        userRepository.saveAll(Arrays.asList(followerUser, followedUser));
    }

    public void unfollowUser(String followerUserId, String unfollowingUserId) {
        if (followerUserId.equals(unfollowingUserId))
            throw new IllegalFollowingArgumentException("You cannot unfollow yourself.");

        User follower = userService.findById(followerUserId);
        User unfollowing = userService.findById(unfollowingUserId);

        Optional<Follow> optionalFollow = followRepository.findByFollowerIdAndFollowedId(followerUserId, unfollowingUserId);

        if (optionalFollow.isEmpty())
            throw new ObjectNotFoundException("Follow relationship not found.");

        followRepository.delete(optionalFollow.get());
        follower.getFollowedIds().remove(unfollowingUserId);
        unfollowing.getFollowersIds().remove(followerUserId);
        userRepository.saveAll(Arrays.asList(follower, unfollowing));
    }
}
