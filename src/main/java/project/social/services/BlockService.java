package project.social.services;

import org.springframework.beans.factory.annotation.Autowired;
import project.social.domain.Block;
import project.social.domain.User;
import project.social.domain.enums.RestrictionType;
import project.social.repositories.BlockRepository;
import project.social.repositories.UserRepository;
import project.social.services.exceptions.BlockAlreadyExistsException;
import project.social.services.exceptions.IllegalBlockingArgumentException;
import project.social.services.exceptions.ObjectNotFoundException;

import java.util.Arrays;
import java.util.Optional;

public class BlockService {

    @Autowired
    private final UserService userService;

    @Autowired
    private final BlockRepository blockRepository;

    @Autowired
    private final UserRepository userRepository;

    public BlockService(UserService userService, BlockRepository blockRepository, UserRepository userRepository) {
        this.userService = userService;
        this.blockRepository = blockRepository;
        this.userRepository = userRepository;
    }

    public void blockUser(String blockerUserId, String blockedUserId) {
        if (blockerUserId.equals(blockedUserId))
            throw new IllegalBlockingArgumentException("You cannot block yourself.");

        User blockerUser = userService.findById(blockerUserId);
        User blockedUser = userService.findById(blockedUserId);

        Optional<Block> optionalBlock = blockRepository.findByBlockerIdAndBlockedId(blockerUserId, blockedUserId);

        if (optionalBlock.isPresent())
            throw new BlockAlreadyExistsException("Block already exists.");

        Block block = Block.builder()
                .blockerId(blockerUserId)
                .blockedId(blockedUserId)
                .type(RestrictionType.BLOCK)
                .build();

        blockRepository.save(block);
        blockerUser.getBlockedUsersIds().add(blockedUserId);
        blockedUser.getBlockedByIds().add(blockerUserId);
        userRepository.saveAll(Arrays.asList(blockerUser, blockedUser));
    }

    public void unblockUser(String unblockerUserId, String unblockedUserId) {
        if (unblockerUserId.equals(unblockedUserId))
            throw new IllegalBlockingArgumentException("You cannot unblock yourself.");

        User unblockerUser = userService.findById(unblockerUserId);
        User unblockedUser = userService.findById(unblockedUserId);

        Optional<Block> optionalBlock = blockRepository.findByBlockerIdAndBlockedId(unblockerUserId, unblockedUserId);

        if (optionalBlock.isEmpty())
            throw new ObjectNotFoundException("Block relationship not found.");

        blockRepository.delete(optionalBlock.get());
        unblockerUser.getBlockedByIds().remove(unblockedUserId);
        unblockedUser.getBlockedUsersIds().remove(unblockerUserId);
        userRepository.saveAll(Arrays.asList(unblockerUser, unblockedUser));
    }
}
