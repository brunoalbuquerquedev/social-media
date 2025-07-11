package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.social.domain.Block;
import project.social.domain.User;
import project.social.domain.enums.RestrictionType;
import project.social.dto.domain.UserDto;
import project.social.exceptions.block.BlockAlreadyExistsException;
import project.social.exceptions.block.InvalidBlockRequestException;
import project.social.mappers.UserMapper;
import project.social.repositories.BlockRepository;
import project.social.repositories.UserRepository;
import project.social.services.interfaces.IBlockService;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlockService implements IBlockService {

    private final UserService userService;
    private final FollowService followService;
    private final UserRepository userRepository;
    private final BlockRepository blockRepository;

    public void blockUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidBlockRequestException("You cannot block yourself.");

        UserDto requesterUserDto = userService.findById(requesterId);
        UserDto targetUserDto = userService.findById(targetId);

        Optional<Block> optionalBlock = blockRepository
                .findByRequesterIdAndTargetIdAndType(requesterId, targetId, RestrictionType.BLOCK);

        if (optionalBlock.isPresent())
            throw new BlockAlreadyExistsException("Block already exists.");

        Block block = Block.builder()
                .blockerUserId(requesterId)
                .blockingUserId(targetId)
                .type(RestrictionType.BLOCK)
                .build();

        blockRepository.save(block);
        followService.deleteMutualFollowingByBlock(requesterId, targetId);

        User requesterUser = UserMapper.fromDto(requesterUserDto);
        User targetUser = UserMapper.fromDto(targetUserDto);

        requesterUser.getUsersBlockedByMe().add(targetId);
        targetUser.getUsersWhoBlockMe().add(requesterId);
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
    }

    public void unblockUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidBlockRequestException("You cannot unblock yourself.");

        UserDto requesterUserDto = userService.findById(requesterId);
        UserDto targetUserDto = userService.findById(targetId);

        Optional<Block> optionalBlock = blockRepository
                .findByRequesterIdAndTargetIdAndType(requesterId, targetId, RestrictionType.BLOCK);

        optionalBlock.ifPresent(blockRepository::delete);

        User requesterUser = UserMapper.fromDto(requesterUserDto);
        User targetUser = UserMapper.fromDto(targetUserDto);

        requesterUser.getUsersBlockedByMe().remove(targetId);
        targetUser.getUsersWhoBlockMe().remove(requesterId);
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
    }

    public void muteUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidBlockRequestException("You cannot block yourself.");

        UserDto requesterUserDto = userService.findById(requesterId);
        UserDto targetUserDto = userService.findById(targetId);

        Optional<Block> optionalBlock = blockRepository
                .findByRequesterIdAndTargetIdAndType(requesterId, targetId, RestrictionType.MUTE);

        if (optionalBlock.isPresent())
            throw new BlockAlreadyExistsException("Mute already exists.");

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

    public void unmuteUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidBlockRequestException("You cannot unmute yourself.");

        UserDto requesterUserDto = userService.findById(requesterId);
        UserDto targetUserDto = userService.findById(targetId);

        Optional<Block> optionalBlock = blockRepository
                .findByRequesterIdAndTargetIdAndType(requesterId, targetId, RestrictionType.MUTE);

        optionalBlock.ifPresent(blockRepository::delete);

        User requesterUser = UserMapper.fromDto(requesterUserDto);
        User targetUser = UserMapper.fromDto(targetUserDto);

        requesterUser.getUsersBlockedByMe().remove(targetId);
        targetUser.getUsersWhoBlockMe().remove(requesterId);
        userRepository.saveAll(Arrays.asList(requesterUser, targetUser));
    }
}
