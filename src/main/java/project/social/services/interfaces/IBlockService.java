package project.social.services.interfaces;

import org.springframework.data.domain.Page;
import project.social.dto.domain.BlockDto;

public interface IBlockService {
    /**
     * Finds all blocks by the given block ID.
     *
     * @param blockId the ID of the block
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @return a paginated list of BlockDto objects
     */
    Page<BlockDto> findAllBlockById(String blockId, int pageNumber, int pageSize);

    /**
     * Blocks a user by their ID.
     *
     * @param requesterId the ID of the user requesting the block
     * @param targetId the ID of the user to be blocked
     */
    void blockUser(String requesterId, String targetId);

    /**
     * Unblocks a user by their ID.
     *
     * @param requesterId the ID of the user requesting the unblock
     * @param targetId the ID of the user to be unblocked
     */
    void unblockUser(String requesterId, String targetId);

    /**
     * Mutes a user by their ID.
     *
     * @param requesterId the ID of the user requesting the mute
     * @param targetId the ID of the user to be muted
     */
    void muteUser(String requesterId, String targetId);

    /**
     * Unmutes a user by their ID.
     *
     * @param requesterId the ID of the user requesting the unmute
     * @param targetId the ID of the user to be unmuted
     */
    void unmuteUser(String requesterId, String targetId);
}
