package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.domain.Block;
import project.social.domain.User;
import project.social.domain.enums.RestrictionType;
import project.social.common.dtos.domain.BlockDto;
import project.social.common.dtos.domain.user.UserDto;
import project.social.common.exceptions.domain.BlockAlreadyExistsException;
import project.social.common.exceptions.domain.InvalidBlockRequestException;
import project.social.domain.mappers.UserMapper;
import project.social.repositories.BlockRepository;
import project.social.repositories.UserRepository;
import project.social.services.interfaces.IBlockService;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockService implements IBlockService {

    private final UserService userService;
    private final FollowService followService;
    private final UserRepository userRepository;
    private final BlockRepository blockRepository;

    @Override
    public Page<BlockDto> findAllBlockById(String blockId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return blockRepository.findAllByBlockerUserId(blockId, pageable).map(BlockDto::new);
    }

    public void blockUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidBlockRequestException("You cannot block yourself.");

        List<UserDto> users = userService.findAllById(List.of(requesterId, targetId));

        blockRepository.findByBlockerUserIdAndBlockingUserIdAndType(requesterId, targetId, RestrictionType.BLOCK)
                .ifPresent(b -> {
                    throw new BlockAlreadyExistsException("Block already exists.");
                });

        Block block = Block.builder()
                .blockerUserId(requesterId)
                .blockingUserId(targetId)
                .type(RestrictionType.BLOCK)
                .createdAt(OffsetDateTime.now())
                .build();

        blockRepository.save(block);
        followService.deleteMutualFollowingByBlock(requesterId, targetId);

        User requesterUser = UserMapper.fromDto(users.getFirst());
        User targetUser = UserMapper.fromDto(users.getLast());

        requesterUser.getUsersBlockedByMe().add(targetId);
        targetUser.getUsersWhoBlockMe().add(requesterId);
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
    }

    @Override
    public void unblockUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidBlockRequestException("You cannot unblock yourself.");

        List<UserDto> users = userService.findAllById(List.of(requesterId, targetId));

        blockRepository.findByBlockerUserIdAndBlockingUserIdAndType(requesterId, targetId, RestrictionType.BLOCK)
                .ifPresent(blockRepository::delete);

        User requesterUser = UserMapper.fromDto(users.getFirst());
        User targetUser = UserMapper.fromDto(users.getLast());

        requesterUser.getUsersBlockedByMe().remove(targetId);
        targetUser.getUsersWhoBlockMe().remove(requesterId);
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
    }

    @Override
    public void muteUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidBlockRequestException("You cannot block yourself.");

        List<UserDto> users = userService.findAllById(List.of(requesterId, targetId));

        blockRepository.findByBlockerUserIdAndBlockingUserIdAndType(requesterId, targetId, RestrictionType.BLOCK)
                .ifPresent(b -> {
                    throw new BlockAlreadyExistsException("Block already exists.");
                });

        Block block = Block.builder()
                .blockerUserId(requesterId)
                .blockingUserId(targetId)
                .type(RestrictionType.MUTE)
                .build();

        blockRepository.save(block);

        User requesterUser = UserMapper.fromDto(users.getFirst());
        User targetUser = UserMapper.fromDto(users.getLast());

        requesterUser.getUsersBlockedByMe().add(targetId);
        targetUser.getUsersWhoBlockMe().add(requesterId);
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
    }

    @Override
    public void unmuteUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidBlockRequestException("You cannot unmute yourself.");

        List<UserDto> users = userService.findAllById(List.of(requesterId, targetId));

        blockRepository.findByBlockerUserIdAndBlockingUserIdAndType(requesterId, targetId, RestrictionType.MUTE)
                .ifPresent(blockRepository::delete);

        User requesterUser = UserMapper.fromDto(users.getFirst());
        User targetUser = UserMapper.fromDto(users.getLast());

        requesterUser.getUsersBlockedByMe().remove(targetId);
        targetUser.getUsersWhoBlockMe().remove(requesterId);
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
    }
}
