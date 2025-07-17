package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.common.dtos.domain.FollowDto;
import project.social.common.dtos.domain.user.UserDto;
import project.social.common.exceptions.domain.FollowAlreadyExistsException;
import project.social.common.exceptions.domain.InvalidFollowRequestException;
import project.social.domain.Follow;
import project.social.domain.User;
import project.social.domain.enums.FollowStatus;
import project.social.domain.mappers.UserMapper;
import project.social.repositories.FollowRepository;
import project.social.repositories.UserRepository;
import project.social.services.interfaces.IFollowService;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService implements IFollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public Page<FollowDto> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return followRepository.findAll(pageable).map(FollowDto::new);
    }

    public Page<FollowDto> findAllById(String id, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return followRepository.findAllByFollowerUserId(id, pageable).map(FollowDto::new);
    }

    @Override
    public void followUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidFollowRequestException("You cannot follow yourself.");

        List<UserDto> users = userService.findAllById(List.of(requesterId, targetId));

        followRepository.findByFollowerUserIdAndFollowingUserId(requesterId, targetId)
                .ifPresent(f -> {
                    throw new FollowAlreadyExistsException("The user is already followed.");
                });

        FollowStatus status = followRepository
                .findByFollowerUserIdAndFollowingUserId(targetId, requesterId)
                .isPresent() ? FollowStatus.MUTUAL_FOLLOWING : FollowStatus.FOLLOWING;

        Follow follow = Follow.builder()
                .followerUserId(requesterId)
                .followingUserId(targetId)
                .status(status)
                .followedAt(OffsetDateTime.now())
                .build();

        followRepository.save(follow);

        User requesterUser = UserMapper.fromDto(users.getFirst());
        User targetUser = UserMapper.fromDto(users.getLast());

        requesterUser.getUsersFollowedByMe().add(targetUser.getId());
        targetUser.getUsersWhoFollowMe().add(requesterUser.getId());
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
    }

    @Override
    public void unfollowUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidFollowRequestException("You cannot unfollow yourself.");

        List<UserDto> users = userService.findAllById(List.of(requesterId, targetId));

        followRepository.findByFollowerUserIdAndFollowingUserId(requesterId, targetId)
                .ifPresent(followRepository::delete);

        User requesterUser = UserMapper.fromDto(users.getFirst());
        User targetUser = UserMapper.fromDto(users.getLast());

        requesterUser.getUsersBlockedByMe().remove(targetId);
        targetUser.getUsersWhoBlockMe().remove(requesterId);
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
    }

    @Override
    public void deleteMutualFollowingByBlock(String requesterId, String targetId) {
        List<Follow> list = new ArrayList<>();

        followRepository.findByFollowerUserIdAndFollowingUserId(requesterId, targetId).ifPresent(list::add);
        followRepository.findByFollowerUserIdAndFollowingUserId(targetId, requesterId).ifPresent(list::add);

        if (!list.isEmpty())
            followRepository.deleteAll(list);
    }
}
