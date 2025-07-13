package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.domain.Follow;
import project.social.domain.User;
import project.social.domain.enums.FollowStatus;
import project.social.dto.domain.FollowDto;
import project.social.dto.domain.UserDto;
import project.social.exceptions.domain.FollowAlreadyExistsException;
import project.social.exceptions.domain.InvalidFollowRequestException;
import project.social.mappers.UserMapper;
import project.social.repositories.FollowRepository;
import project.social.repositories.UserRepository;
import project.social.services.interfaces.IFollowService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        return followRepository.findAllByRequesterId(id, pageable).map(FollowDto::new);
    }

    public void followUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidFollowRequestException("You cannot follow yourself.");

        UserDto requesterUserDto = userService.findById(requesterId);
        UserDto targetUserDto = userService.findById(targetId);

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

        User requesterUser = UserMapper.fromDto(requesterUserDto);
        User targetUser = UserMapper.fromDto(targetUserDto);

        requesterUser.getUsersFollowedByMe().add(targetUser.getId());
        targetUser.getUsersWhoFollowMe().add(requesterUser.getId());
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
    }

    public void unfollowUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidFollowRequestException("You cannot unfollow yourself.");

        UserDto requesterUserDto = userService.findById(requesterId);
        UserDto targetUserDto = userService.findById(targetId);

        Optional<Follow> optionalFollow = followRepository
                .findByRequesterIdAndTargetId(requesterId, targetId);

        optionalFollow.ifPresent(followRepository::delete);

        User requesterUser = UserMapper.fromDto(requesterUserDto);
        User targetUser = UserMapper.fromDto(targetUserDto);

        requesterUser.getUsersBlockedByMe().remove(targetId);
        targetUser.getUsersWhoBlockMe().remove(requesterId);
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
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
