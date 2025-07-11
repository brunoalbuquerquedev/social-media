package project.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.social.domain.Block;
import project.social.domain.User;
import project.social.domain.enums.RestrictionType;
import project.social.exceptions.block.BlockAlreadyExistsException;
import project.social.exceptions.block.InvalidBlockRequestException;
import project.social.repositories.BlockRepository;
import project.social.repositories.UserRepository;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final UserService userService;
    private final FollowService followService;
    private final UserRepository userRepository;
    private final BlockRepository blockRepository;

    public void blockUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidBlockRequestException("You cannot block yourself.");

        User blockerUser = userService.findById(requesterId);
        User blockedUser = userService.findById(targetId);

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
        blockerUser.getUsersWhoBlockedMe().add(targetId);
        blockedUser.getUsersBlockedByMe().add(requesterId);
        userRepository.saveAll(Arrays.asList(blockerUser, blockedUser));
    }

    public void unblockUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidBlockRequestException("You cannot unblock yourself.");

        User unblockerUser = userService.findById(requesterId);
        User unblockedUser = userService.findById(targetId);

        Optional<Block> optionalBlock = blockRepository
                .findByRequesterIdAndTargetIdAndType(requesterId, targetId, RestrictionType.BLOCK);

        optionalBlock.ifPresent(blockRepository::delete);
        unblockerUser.getUsersWhoBlockedMe().remove(targetId);
        unblockedUser.getUsersBlockedByMe().remove(requesterId);
        userRepository.saveAll(Arrays.asList(unblockerUser, unblockedUser));
    }

    public void muteUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidBlockRequestException("You cannot block yourself.");

        User blockerUser = userService.findById(requesterId);
        User blockedUser = userService.findById(targetId);

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
        blockerUser.getUsersBlockedByMe().add(targetId);
        blockedUser.getUsersWhoBlockedMe().add(requesterId);
        userRepository.saveAll(Arrays.asList(blockerUser, blockedUser));
    }

    public void unmuteUser(String requesterId, String targetId) {
        if (requesterId.equals(targetId))
            throw new InvalidBlockRequestException("You cannot unmute yourself.");

        User unblockerUser = userService.findById(requesterId);
        User unblockingUser = userService.findById(targetId);

        Optional<Block> optionalBlock = blockRepository
                .findByRequesterIdAndTargetIdAndType(requesterId, targetId, RestrictionType.MUTE);

        optionalBlock.ifPresent(blockRepository::delete);
        unblockerUser.getUsersBlockedByMe().remove(targetId);
        unblockingUser.getUsersWhoBlockedMe().remove(requesterId);
        userRepository.saveAll(Arrays.asList(unblockerUser, unblockingUser));
    }
}
