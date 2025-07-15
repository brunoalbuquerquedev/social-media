package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.social.domain.Block;
import project.social.domain.User;
import project.social.domain.enums.RestrictionType;
import project.social.dto.domain.BlockDto;
import project.social.dto.domain.UserDto;
import project.social.exceptions.domain.BlockAlreadyExistsException;
import project.social.exceptions.domain.InvalidBlockRequestException;
import project.social.mappers.UserMapper;
import project.social.repositories.BlockRepository;
import project.social.repositories.UserRepository;
import project.social.services.interfaces.IBlockService;

import java.time.OffsetDateTime;
import java.util.Arrays;

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

        UserDto requesterUserDto = userService.findById(requesterId);
        UserDto targetUserDto = userService.findById(targetId);

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

        User requesterUser = UserMapper.fromDto(requesterUserDto);
        User targetUser = UserMapper.fromDto(targetUserDto);

        requesterUser.getUsersBlockedByMe().add(targetId);
        targetUser.getUsersWhoBlockMe().add(requesterId);
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
    }

    @Override
    public void unblockUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidBlockRequestException("You cannot unblock yourself.");

        UserDto requesterUserDto = userService.findById(requesterId);
        UserDto targetUserDto = userService.findById(targetId);

        blockRepository.findByBlockerUserIdAndBlockingUserIdAndType(requesterId, targetId, RestrictionType.BLOCK)
                .ifPresent(blockRepository::delete);

        User requesterUser = UserMapper.fromDto(requesterUserDto);
        User targetUser = UserMapper.fromDto(targetUserDto);

        requesterUser.getUsersBlockedByMe().remove(targetId);
        targetUser.getUsersWhoBlockMe().remove(requesterId);
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
    }

    @Override
    public void muteUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidBlockRequestException("You cannot block yourself.");

        UserDto requesterUserDto = userService.findById(requesterId);
        UserDto targetUserDto = userService.findById(targetId);

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

        User requesterUser = UserMapper.fromDto(requesterUserDto);
        User targetUser = UserMapper.fromDto(targetUserDto);

        requesterUser.getUsersBlockedByMe().add(targetId);
        targetUser.getUsersWhoBlockMe().add(requesterId);
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
    }

    @Override
    public void unmuteUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidBlockRequestException("You cannot unmute yourself.");

        UserDto requesterUserDto = userService.findById(requesterId);
        UserDto targetUserDto = userService.findById(targetId);

        blockRepository.findByBlockerUserIdAndBlockingUserIdAndType(requesterId, targetId, RestrictionType.MUTE)
                .ifPresent(blockRepository::delete);

        User requesterUser = UserMapper.fromDto(requesterUserDto);
        User targetUser = UserMapper.fromDto(targetUserDto);

        requesterUser.getUsersBlockedByMe().remove(targetId);
        targetUser.getUsersWhoBlockMe().remove(requesterId);
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
    }
}
